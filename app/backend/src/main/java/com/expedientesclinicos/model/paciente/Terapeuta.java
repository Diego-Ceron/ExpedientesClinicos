package com.expedientesclinicos.model.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "terapeutas")
public class Terapeuta extends Usuario {

    @Column(nullable = false, length = 120)
    private String especialidad;

    @Column(nullable = false, length = 60, unique = true)
    private String cedulaProfesional;

    @Column(length = 30)
    private String telefono;

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
