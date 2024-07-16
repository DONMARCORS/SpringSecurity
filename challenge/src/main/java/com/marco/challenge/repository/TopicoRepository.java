package com.marco.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.challenge.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}