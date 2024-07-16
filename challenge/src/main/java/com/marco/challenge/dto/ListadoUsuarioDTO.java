package com.marco.challenge.dto;

import com.marco.challenge.modelo.Usuario;

public record ListadoUsuarioDTO(Long id, String nombre, String email, String tipo) {

    public ListadoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getTipo().toString());
    }
}