# Estructura de clases

## Clase _Paciente_
Representa a un paciente dentro del sistema. Contiene información personal.
```java
public class Paciente {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private LocalDate fechaNacimiento;
    private String telefono;
}
```
## Clase _Sesión_
Representa una sesión psicológica realizada a un paciente. Incluye detalles como fecha, terapeuta, tipo de sesión y notas.
```java
public class Sesion {
    private Long id;
    private LocalDate fecha;
    private String terapeuta;
    private String notas;
    private String TiposSesion tipoSesion;
    private int duracionMinutos;
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
    SEGUIMIENT("Sesión de seguimiento"),
    CRISIS("Intervención en situación crítica"),
    PSICOEDUCATIVA("Sesión educativa sobre salud mental")
}
```
---
[Vover al inicio](../README.md)