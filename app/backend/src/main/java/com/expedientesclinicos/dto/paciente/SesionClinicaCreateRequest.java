package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.model.paciente.EstadoAsistencia;
import com.expedientesclinicos.model.paciente.TipoSesion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class SesionClinicaCreateRequest {

    @Valid
    @NotNull
    private ModificadorRequest solicitante;

    @NotNull
    private Long terapeutaId;

    @NotNull
    private Integer numeroSesion;

    @NotNull
    private TipoSesion tipoSesion;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private EstadoAsistencia asistencia;

    @Min(0)
    private Integer duracionMinutos;

    private String motivoCancelacion;

    @NotBlank
    private String descripcion;

    private String observaciones;

    public ModificadorRequest getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(ModificadorRequest solicitante) {
        this.solicitante = solicitante;
    }
    public Long getTerapeutaId() {
        return terapeutaId;
    }

    public void setTerapeutaId(Long terapeutaId) {
        this.terapeutaId = terapeutaId;
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
