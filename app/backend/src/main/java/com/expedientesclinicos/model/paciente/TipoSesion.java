package com.expedientesclinicos.model.paciente;

public enum TipoSesion {
    INDIVIDUAL("Sesión uno a uno"),
    FAMILIAR("Sesión con familia"),
    GRUPAL("Sesión grupal"),
    EVALUACION_INICIAL("Evaluación inicial del paciente"),
    SEGUIMIENTO("Sesión de seguimiento"),
    CRISIS("Intervención en situación crítica"),
    PSICOEDUCATIVA("Sesión educativa sobre salud mental");

    private final String descripcion;

    TipoSesion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
