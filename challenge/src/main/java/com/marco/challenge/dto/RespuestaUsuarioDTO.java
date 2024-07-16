package com.marco.challenge.dto;

import com.marco.challenge.modelo.Usuario;

public record RespuestaUsuarioDTO(String nombre, String email, String tipo) {

    public RespuestaUsuarioDTO(Usuario usuario) {
        this(usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}