package com.marco.challenge.dto;

import com.marco.challenge.modelo.Respuesta;


public record RetornoRespuestaIdDTO(String mensaje, RespuestaTopicoDTO topico, String fechaCreacion, RespuestaUsuarioDTO autor, String solucion) {

    public RetornoRespuestaIdDTO(Respuesta respuesta) {
        this(respuesta.getMensaje(), new RespuestaTopicoDTO(respuesta.getTopico()), respuesta.getFechaCreacion().toString(),
                new RespuestaUsuarioDTO(respuesta.getAutor()), respuesta.getSolucion().toString());
    }
}