package com.marco.challenge.dto;

import com.marco.challenge.modelo.Respuesta;

public record ListadoRespuestaDTO(Long id, String mensaje, String topico, String autor) {

    public ListadoRespuestaDTO(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}