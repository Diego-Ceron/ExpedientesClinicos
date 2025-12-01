# Requisitos/Historias de Usuario

## Priorización de requisitos funcionales

| ID    | Descripción                                                   | Prioridad |
| ----- | ------------------------------------------------------------- | --------- |
| RF-01 | Creación, edición y eliminación de expedientes                | **M**     |
| RF-02 | Búsqueda de expedientes por nombre del paciente               | **M**     |
| RF-03 | Consulta y edición de pacientes asignados a terapeutas        | **M**     |
| RF-04 | Registro de control de sesiones (datos relevantes)            | **M**     |
| RF-05 | Edición de hojas de control de sesión                         | **M**     |
| RF-06 | Subida de archivos relevantes en sesiones                     | **S**     |
| RF-07 | Procesamiento/exportación digital de apartados del expediente | **S**     |
| RF-08 | Restricción de acceso a información (roles, permisos)         | **M**     |

## Priorización de requisitos no funcionales

| ID     | Descripción                                                  | Prioridad |
| ------ | ------------------------------------------------------------ | --------- |
| RNF-01 | Consulta y actualización rápida (≤ 5 min terapeutas)         | **M**     |
| RNF-02 | Facilidad de aprendizaje (≤ 2 días)                          | **M**     |
| RNF-03 | Organización clara de la información                         | **S**     |
| RNF-04 | Minimización de errores (validaciones, confirmaciones)       | **M**     |
| RNF-05 | Consistencia en la interfaz                                  | **S**     |
| RNF-06 | Seguridad en el manejo de datos (cifrado, roles, privacidad) | **M**     |
| RNF-07 | Rendimiento y escalabilidad (≤ 2s, multiusuario)             | **S**     |
| RNF-08 | Compatibilidad (Chrome, Firefox, Edge)                       | **M**     |
| RNF-09 | Mantenibilidad (código modular, documentación)               | **S**     |
| RNF-10 | Confiabilidad y disponibilidad (≥ 95%, respaldos)            | **M**     |

## Priorización de historias de usuario

Terapeutas
* Must Have: Crear/editar expedientes, registrar/editar sesiones, consultar historial, adjuntar archivos.
* Should Have: Exportar historial a PDF, imprimir expediente.
* Could Have: Visualizar archivos sin descarga, recordatorios automáticos.
Administrativos
* Must Have: Crear usuarios, asignar pacientes, definir roles, consultar expedientes.
* Should Have: Generar reportes de sesiones y carga de trabajo, exportar reportes.
* Could Have: Estadísticas avanzadas, alertas automáticas de inactividad.

---
[Volver al inicio](../../README.md)
