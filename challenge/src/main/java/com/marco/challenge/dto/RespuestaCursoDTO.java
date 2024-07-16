package com.marco.challenge.dto;

import com.marco.challenge.modelo.Curso;

public record RespuestaCursoDTO(String nombre, String categoria) {

    public RespuestaCursoDTO(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}