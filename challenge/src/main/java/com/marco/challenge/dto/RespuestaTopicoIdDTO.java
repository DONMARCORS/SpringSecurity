package com.marco.challenge.dto;

import com.marco.challenge.modelo.Topico;

public record RespuestaTopicoIdDTO(Long id, String titulo, String mensaje, String fechaCreacion, String estado, RespuestaUsuarioDTO autor,
                                     RespuestaCursoDTO curso) {

    public RespuestaTopicoIdDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), new RespuestaUsuarioDTO(topico.getAutor()),
                new RespuestaCursoDTO(topico.getCurso()));
    }
}