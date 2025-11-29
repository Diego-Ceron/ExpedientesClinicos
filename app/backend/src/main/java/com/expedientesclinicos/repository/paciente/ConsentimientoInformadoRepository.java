package com.expedientesclinicos.repository.paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedientesclinicos.model.paciente.ConsentimientoInformado;

public interface ConsentimientoInformadoRepository extends JpaRepository<ConsentimientoInformado, Long> {
    Optional<ConsentimientoInformado> findByPacienteId(Long pacienteId);
}
