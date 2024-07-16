package com.marco.challenge.dto;

import com.marco.challenge.modelo.Tipo;

public record ActualizarUsuarioDTO(Long id, String nombre, String email, String contrasena, Tipo tipo) {
}