package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.model.paciente.EstadoAsistencia;
import com.expedientesclinicos.model.paciente.TipoSesion;

import java.time.LocalDate;

public class SesionClinicaResponse {

    private Long id;
    private Integer numeroSesion;
    private TipoSesion tipoSesion;
    private LocalDate fecha;
    private EstadoAsistencia asistencia;
    private Integer duracionMinutos;
    private String motivoCancelacion;
    private String descripcion;
    private String observaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroSesion() {
        return numeroSesion;
    }

    public void setNumeroSesion(Integer numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    public TipoSesion getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(TipoSesion tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoAsistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(EstadoAsistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
