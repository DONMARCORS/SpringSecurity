package com.marco.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ActualizarRespuestaDTO(
        @NotNull
        Long id,

        @NotBlank
        String mensaje,

        @NotNull
        Long topicoId,

        @NotBlank
        Long autorId,

        @NotNull
        Boolean solucion) {
}