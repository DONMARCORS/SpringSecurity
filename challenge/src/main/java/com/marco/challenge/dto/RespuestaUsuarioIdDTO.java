package com.marco.challenge.dto;

import com.marco.challenge.modelo.Usuario;

public record RespuestaUsuarioIdDTO(String nombre, String email, String contrasena, String tipo) {

    public RespuestaUsuarioIdDTO(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getTipo().toString());
    }
}