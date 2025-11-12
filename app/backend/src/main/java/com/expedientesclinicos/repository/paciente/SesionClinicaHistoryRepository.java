package com.expedientesclinicos.repository.paciente;

import com.expedientesclinicos.model.paciente.SesionClinicaHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionClinicaHistoryRepository extends JpaRepository<SesionClinicaHistory, Long> {

    List<SesionClinicaHistory> findBySesionIdOrderByFechaModificacionDesc(Long sesionId);
}
