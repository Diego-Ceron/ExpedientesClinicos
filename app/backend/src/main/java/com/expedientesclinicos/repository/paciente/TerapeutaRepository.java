package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
	Optional<Terapeuta> findByCorreoIgnoreCase(String correo);
}
