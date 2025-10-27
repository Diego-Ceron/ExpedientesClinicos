## Backend – Expedientes Clínicos

### Organización de carpetas

```
app/backend/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/expedientesclinicos/
│   │   │   ├── ExpedientesClinicosApplication.java
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   └── test/ (sin pruebas por ahora)
└── target/ (generado por Maven, ignorado en git)
```

### Código fuente (`src/main/java`)

- `ExpedientesClinicosApplication`: clase de arranque de Spring Boot; habilita la auto-configuración y dispara Flyway en el inicio.

#### Paquete `controller`
- `RestExceptionHandler`: traduce las excepciones de la capa de servicio en respuestas JSON coherentes (`400`, `403`, `404`) para el cliente.
- `paciente/PacienteController`: endpoints bajo `/api/pacientes`. Recibe los DTOs validados y delega la lógica a `PacienteService`.
- `paciente/SesionClinicaController`: administra `/api/pacientes/{pacienteId}/sesiones`, permitiendo registrar, actualizar y listar sesiones clínicas.

#### Paquete `dto`
- `common/ModificadorRequest`: contiene `usuarioId` y `perfil` del solicitante. Se envía en cada operación para validar permisos.
- `common/PerfilSolicitante`: enum con los perfiles `TERAPEUTA` y `ADMINISTRADOR`.
- `paciente/PacienteCreateRequest` y `PacienteUpdateRequest`: datos necesarios para crear o actualizar un paciente (nombre, contacto, servicio, estado, sexo, edad, fechas y terapeuta asignado). Usan anotaciones de Bean Validation (`@NotNull`, `@NotBlank`, `@Email`, `@Min`).
- `paciente/PacienteResponse`: DTO de salida para pacientes, incluye información básica y `TerapeutaSummary`.
- `paciente/TerapeutaSummary`: resumen de un terapeuta (id, nombre, especialidad) utilizado en respuestas.
- `paciente/SesionClinicaCreateRequest` y `SesionClinicaUpdateRequest`: payloads para registrar o modificar sesiones (número, tipo, fecha, asistencia, duración, motivo, descripción y observaciones).
- `paciente/SesionClinicaResponse`: representación de una sesión almacenada.

#### Paquete `exception`
- `DomainException`: base para errores de reglas de negocio.
- `ResourceNotFoundException`: se lanza para recursos inexistentes (HTTP 404).
- `AccessDeniedException`: indica falta de permisos (HTTP 403).

#### Paquete `model`
- `Usuario`: clase base con atributos comunes (`id`, `nombre`, `correo`, `password`, `rol`).
- `Terapeuta` y `Administrativo`: especializaciones de `Usuario` con campos propios.
- `Paciente`: entidad principal con datos personales, de contacto y la relación con `Terapeuta`.
- `SesionClinica`: representación de una sesión terapéutica ligada a un paciente y terapeuta.
- Enums auxiliares: `EstadoExpediente`, `EstadoAsistencia`, `TipoServicio`, `TipoSesion`, `Sexo`.

#### Paquete `repository`
- `PacienteRepository`: interfaz `JpaRepository` con consultas derivadas para filtrar por terapeuta.
- `SesionClinicaRepository`: repositorio JPA con búsquedas por paciente y por identificador compuesto paciente/sesión.
- `TerapeutaRepository`: acceso CRUD a terapeutas.

#### Paquete `service`
- `PacienteService`: aplica las reglas de negocio sobre pacientes (validación del solicitante, control de acceso por terapeuta, mapeo entidad/DTO, borrado seguro).
- `SesionClinicaService`: orquesta la gestión de sesiones, verifica que el paciente exista, que el solicitante tenga permisos y que la sesión pertenezca al terapeuta asignado.

### Recursos (`src/main/resources`)
- `application.properties`: configura la base H2 en memoria con consola habilitada, define `spring.jpa.hibernate.ddl-auto=none`, formatea el SQL y activa Flyway.
- `db/migration/V1__init_schema.sql`: migración inicial que crea las tablas `terapeutas`, `administrativos`, `pacientes` y `sesiones_clinicas`, además de sus índices y claves foráneas.

### Construcción y ejecución

```bash
mvn clean install
mvn spring-boot:run
```
