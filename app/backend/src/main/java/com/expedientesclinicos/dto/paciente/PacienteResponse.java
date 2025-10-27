package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.model.paciente.EstadoExpediente;
import com.expedientesclinicos.model.paciente.Sexo;
import com.expedientesclinicos.model.paciente.TipoServicio;

import java.time.LocalDate;

public class PacienteResponse {

    private Long id;
    private String nombreCompleto;
    private String celular;
    private String email;
    private TipoServicio tipoServicio;
    private EstadoExpediente estado;
    private Sexo sexo;
    private Integer edad;
    private LocalDate fechaRegistro;
    private LocalDate fechaProximaSesion;
    private TerapeutaSummary terapeutaAsignado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public EstadoExpediente getEstado() {
        return estado;
    }

    public void setEstado(EstadoExpediente estado) {
        this.estado = estado;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaProximaSesion() {
        return fechaProximaSesion;
    }

    public void setFechaProximaSesion(LocalDate fechaProximaSesion) {
        this.fechaProximaSesion = fechaProximaSesion;
    }

    public TerapeutaSummary getTerapeutaAsignado() {
        return terapeutaAsignado;
    }

    public void setTerapeutaAsignado(TerapeutaSummary terapeutaAsignado) {
        this.terapeutaAsignado = terapeutaAsignado;
    }
}
