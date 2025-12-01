package com.expedientesclinicos.repository.paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedientesclinicos.model.paciente.EntrevistaInicial;

public interface EntrevistaInicialRepository extends JpaRepository<EntrevistaInicial, Long> {
    Optional<EntrevistaInicial> findByPacienteId(Long pacienteId);
}
