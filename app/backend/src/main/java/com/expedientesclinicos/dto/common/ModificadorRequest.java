package com.expedientesclinicos.dto.common;

import jakarta.validation.constraints.NotNull;

public class ModificadorRequest {

    @NotNull
    private Long usuarioId;

    @NotNull
    private PerfilSolicitante perfil;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public PerfilSolicitante getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilSolicitante perfil) {
        this.perfil = perfil;
    }

    public boolean esAdministrador() {
        return PerfilSolicitante.ADMINISTRADOR.equals(perfil);
    }

    public static ModificadorRequest of(Long usuarioId, PerfilSolicitante perfil) {
        ModificadorRequest request = new ModificadorRequest();
        request.setUsuarioId(usuarioId);
        request.setPerfil(perfil);
        return request;
    }
}
