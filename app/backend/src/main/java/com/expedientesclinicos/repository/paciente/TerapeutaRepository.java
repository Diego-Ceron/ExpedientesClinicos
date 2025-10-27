package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
}
