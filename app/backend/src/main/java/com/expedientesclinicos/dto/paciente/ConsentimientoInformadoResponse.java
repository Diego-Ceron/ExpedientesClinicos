package com.expedientesclinicos.dto.paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsentimientoInformadoResponse {
    private Long id;
    private String alcance;
    private String riesgos;
    private String aceptacion;
    private LocalDate fechaRegistro;
    private LocalDateTime ultimaActualizacion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAlcance() { return alcance; }
    public void setAlcance(String alcance) { this.alcance = alcance; }
    public String getRiesgos() { return riesgos; }
    public void setRiesgos(String riesgos) { this.riesgos = riesgos; }
    public String getAceptacion() { return aceptacion; }
    public void setAceptacion(String aceptacion) { this.aceptacion = aceptacion; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}
