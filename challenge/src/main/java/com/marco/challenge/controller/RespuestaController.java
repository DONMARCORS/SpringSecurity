package com.marco.challenge.controller;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.marco.challenge.dto.ActualizarRespuestaDTO;
import com.marco.challenge.dto.ListadoRespuestaDTO;
import com.marco.challenge.dto.RegistroRespuestaDTO;
import com.marco.challenge.dto.RetornoRespuestaDTO;
import com.marco.challenge.dto.RetornoRespuestaIdDTO;
import com.marco.challenge.modelo.Estado;
import com.marco.challenge.modelo.Respuesta;
import com.marco.challenge.modelo.Topico;
import com.marco.challenge.modelo.Usuario;
import com.marco.challenge.repository.RespuestaRepository;
import com.marco.challenge.repository.TopicoRepository;
import com.marco.challenge.repository.UsuarioRepository;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public RespuestaController(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RetornoRespuestaDTO> registrar(@RequestBody RegistroRespuestaDTO datosRegistro, UriComponentsBuilder uri) {
        Topico topico = topicoRepository.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == Estado.CERRADO) {
           return ResponseEntity.unprocessableEntity().build();
        }

        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        RetornoRespuestaDTO datosRespuesta = new RetornoRespuestaDTO(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoRespuestaDTO>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(ListadoRespuestaDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornoRespuestaIdDTO> retornaDatos(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new RetornoRespuestaIdDTO(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RetornoRespuestaDTO> actualizar(@RequestBody ActualizarRespuestaDTO datosActualizar) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizar.id());
        Topico topico = topicoRepository.getReferenceById(datosActualizar.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(datosActualizar.autorId());
        respuesta.actualizarDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new RetornoRespuestaDTO(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}