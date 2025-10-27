package com.expedientesclinicos.model.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrativos")
public class Administrativo extends Usuario {

    @Column(nullable = false, length = 120)
    private String departamento;

    @Column(nullable = false, length = 120)
    private String puesto;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
