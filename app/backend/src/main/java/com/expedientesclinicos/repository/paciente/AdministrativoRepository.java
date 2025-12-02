package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
    Optional<Administrativo> findByCorreoIgnoreCase(String correo);
}
