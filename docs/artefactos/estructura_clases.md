# Estructura de clases

## Clase _Usuario_
Atributos comunes a cualquier persona que usa el sistema.
```java
public abstract class Usuario {
    protected Long id;
    protected String nombre;
    protected String correo;
    protected String password;
    protected String rol;
}
```
## Clase _Terapeuta_
Representa a un terapeuta que atiende a los pacientes.
```java
public class Terapeuta extends Usuario {
    private String especialidad;
    private String cedulaProfesional;
    private String telefono;
}
```
## Clase _Administrativo_
```java
public class Administrativo extends Usuario {
    private String departamento;
    private String puesto;
}
```
## Enum _TipoSesion_
Define los tipos de sesión disponibles en el sistema.
```java
public enum TipoSesion {
    INDIVIDUAL("Sesión uno a uno"),
    FAMILIAR("Sesión con familia"),
    GRUPAL("Sesión grupal"),
    EVALUACION_INICIAL("Evaluación inicial del paciente"),
    SEGUIMIENTO("Sesión de seguimiento"),
    CRISIS("Intervención en situación crítica"),
    PSICOEDUCATIVA("Sesión educativa sobre salud mental")
}
```
---
[Volver al inicio](../../README.md)