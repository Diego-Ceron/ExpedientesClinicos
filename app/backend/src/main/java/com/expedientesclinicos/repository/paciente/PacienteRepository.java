package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByTerapeutaAsignadoId(Long terapeutaId);

    Optional<Paciente> findByIdAndTerapeutaAsignadoId(Long pacienteId, Long terapeutaId);
}
