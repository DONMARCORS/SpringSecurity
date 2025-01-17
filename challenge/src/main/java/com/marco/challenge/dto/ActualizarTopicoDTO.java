package com.marco.challenge.dto;

import com.marco.challenge.modelo.Estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Estado estado,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId
    ) {
}