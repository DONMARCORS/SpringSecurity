package com.marco.challenge.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.marco.challenge.dto.ActualizarTopicoDTO;
import com.marco.challenge.dto.ListadoTopicoDTO;
import com.marco.challenge.dto.RegistroTopicoDTO;
import com.marco.challenge.dto.RespuestaTopicoDTO;
import com.marco.challenge.dto.RespuestaTopicoIdDTO;
import com.marco.challenge.modelo.Curso;
import com.marco.challenge.modelo.Topico;
import com.marco.challenge.modelo.Usuario;
import com.marco.challenge.repository.CursoRepository;
import com.marco.challenge.repository.TopicoRepository;
import com.marco.challenge.repository.UsuarioRepository;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaTopicoDTO> registrar(@RequestBody @Valid RegistroTopicoDTO datosRegistro, UriComponentsBuilder uri) {
        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
        Curso curso = cursoRepository.getReferenceById(datosRegistro.cursoId());
        Topico topico = topicoRepository.save(new Topico(datosRegistro, autor, curso));
        RespuestaTopicoDTO datosRespuesta = new RespuestaTopicoDTO(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicoDTO>> listar(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(ListadoTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoIdDTO> retornaDatos(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaTopicoIdDTO(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizar(@RequestBody @Valid ActualizarTopicoDTO datosActualizar) {
        Usuario autor = usuarioRepository.getReferenceById(datosActualizar.autorId());
        Curso curso = cursoRepository.getReferenceById(datosActualizar.cursoId());
        Topico topico = topicoRepository.getReferenceById(datosActualizar.id());
        topico.actualizarDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok( new RespuestaTopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        //topicoRepository.delete(topico);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}