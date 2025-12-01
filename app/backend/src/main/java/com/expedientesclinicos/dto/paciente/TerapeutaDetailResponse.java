package com.expedientesclinicos.dto.paciente;

public class TerapeutaDetailResponse {

    private Long id;
    private String nombre;
    private String correo;
    private String especialidad;
    private String cedulaProfesional;
    private String telefono;

    public TerapeutaDetailResponse() {
    }

    public TerapeutaDetailResponse(Long id,
                                   String nombre,
                                   String correo,
                                   String especialidad,
                                   String cedulaProfesional,
                                   String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
