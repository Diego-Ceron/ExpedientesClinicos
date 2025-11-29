package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EntrevistaInicialRequest {

    @Valid
    @NotNull
    private ModificadorRequest solicitante;

    @NotBlank
    private String motivoConsulta;

    private String antecedentes;
    private String observaciones;

    public ModificadorRequest getSolicitante() { return solicitante; }
    public void setSolicitante(ModificadorRequest solicitante) { this.solicitante = solicitante; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }
    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
