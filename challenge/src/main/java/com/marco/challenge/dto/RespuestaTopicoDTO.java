package com.marco.challenge.dto;

import com.marco.challenge.modelo.Topico;

public record RespuestaTopicoDTO(String titulo, String mensaje, String autor, String curso) {

    public RespuestaTopicoDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}