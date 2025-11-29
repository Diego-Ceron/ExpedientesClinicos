package com.expedientesclinicos.dto.paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntrevistaInicialResponse {
    private Long id;
    private String motivoConsulta;
    private String antecedentes;
    private String observaciones;
    private LocalDate fechaRegistro;
    private LocalDateTime ultimaActualizacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }
    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}
