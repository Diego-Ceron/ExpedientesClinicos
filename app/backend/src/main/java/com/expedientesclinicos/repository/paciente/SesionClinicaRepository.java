package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.SesionClinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SesionClinicaRepository extends JpaRepository<SesionClinica, Long> {

    List<SesionClinica> findByPacienteId(Long pacienteId);

    Optional<SesionClinica> findByIdAndPacienteId(Long sesionId, Long pacienteId);
}
