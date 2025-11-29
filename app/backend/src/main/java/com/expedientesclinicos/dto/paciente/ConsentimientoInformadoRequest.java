package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ConsentimientoInformadoRequest {

    @Valid
    @NotNull
    private ModificadorRequest solicitante;

    @NotBlank
    private String alcance;

    private String riesgos;

    @NotBlank
    private String aceptacion;

    public ModificadorRequest getSolicitante() { return solicitante; }
    public void setSolicitante(ModificadorRequest solicitante) { this.solicitante = solicitante; }
    public String getAlcance() { return alcance; }
    public void setAlcance(String alcance) { this.alcance = alcance; }
    public String getRiesgos() { return riesgos; }
    public void setRiesgos(String riesgos) { this.riesgos = riesgos; }
    public String getAceptacion() { return aceptacion; }
    public void setAceptacion(String aceptacion) { this.aceptacion = aceptacion; }
}
