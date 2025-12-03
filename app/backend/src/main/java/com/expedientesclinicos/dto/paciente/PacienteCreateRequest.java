package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;
import com.expedientesclinicos.model.paciente.EstadoExpediente;
import com.expedientesclinicos.model.paciente.Sexo;
import com.expedientesclinicos.model.paciente.TipoServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class PacienteCreateRequest {

    @Valid
    @NotNull
    private ModificadorRequest solicitante;

    @NotBlank
    private String nombreCompleto;

    @NotBlank
    private String celular;

    @Email
    private String email;

    @NotNull
    private TipoServicio tipoServicio;

    @NotNull
    private EstadoExpediente estado;

    @NotNull
    private Sexo sexo;

    @Min(0)
    private Integer edad;

    @NotNull
    private LocalDate fechaRegistro;

    private LocalDate fechaProximaSesion;

    @Positive
    private Long terapeutaId;

    public ModificadorRequest getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(ModificadorRequest solicitante) {
        this.solicitante = solicitante;
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

    public Long getTerapeutaId() {
        return terapeutaId;
    }

    public void setTerapeutaId(Long terapeutaId) {
        this.terapeutaId = terapeutaId;
    }
}
