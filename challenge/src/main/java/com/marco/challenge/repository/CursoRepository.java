package com.marco.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marco.challenge.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}