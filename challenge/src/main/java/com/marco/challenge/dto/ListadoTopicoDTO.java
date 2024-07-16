package com.marco.challenge.dto;

import com.marco.challenge.modelo.Topico;

public record ListadoTopicoDTO(Long id, String titulo, String mensaje, String autor, String curso) {

    public ListadoTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }
}