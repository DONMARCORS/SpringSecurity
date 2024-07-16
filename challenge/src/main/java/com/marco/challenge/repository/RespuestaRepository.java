package com.marco.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.challenge.modelo.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}