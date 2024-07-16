package com.marco.challenge.dto;

import com.marco.challenge.modelo.Tipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroUsuarioDTO(
        @NotBlank
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String contrasena,

        @NotNull
        Tipo tipo
    ) {
}