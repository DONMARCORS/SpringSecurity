package com.marco.challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacionUsuarioDTO(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String contrasena
    ) {
}