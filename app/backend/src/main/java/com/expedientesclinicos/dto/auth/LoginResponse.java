package com.expedientesclinicos.dto.auth;

public class LoginResponse {
    private Long usuarioId;
    private String nombre;
    private String rol;

    public LoginResponse() {}
    public LoginResponse(Long usuarioId, String nombre, String rol) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
