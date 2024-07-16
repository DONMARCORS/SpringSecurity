package com.marco.challenge.dto;

import com.marco.challenge.modelo.Respuesta;

public record RetornoRespuestaDTO(String mensaje, String topico, String autor) {

    public RetornoRespuestaDTO(Respuesta respuesta) {
        this(respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre());
    }
}