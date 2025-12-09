# Contribución Individual

Este documento detalla, en tres etapas cronológicas, las aportaciones específicas de cada integrante al proyecto "Expedientes Clínicos". Se incluyen tanto tareas técnicas como de documentación y soporte operativo.

---
## Etapa 1: Descubrimiento y Alcance
Foco: definición conceptual, documentación base y organización inicial del repositorio.

### Christian
- Organización inicial de la estructura de carpetas y convención de documentos en `docs/`.
- Documentación creada: Requisitos No Funcionales, Estructura de Clases, Descripción del Proceso, Gestión del Proceso, Objetivo del sistema.
- Diagramas: elaboración de diagrama UML preliminar (vista de clases y relaciones) y diagrama de Casos de Uso.
- Alineación de terminología entre documentos (paciente, terapeuta, sesión clínica) para evitar ambigüedades posteriores.

### Mauricio 
- Documentación funcional: redacción de Requisitos Funcionales y su desglose en Historias de Usuario priorizadas.
- Documentos de alcance, análisis de factibilidad y limitaciones (riesgos técnicos y organizativos).
- Elaboración de métodos de priorización y matriz de valor vs esfuerzo; propuesta de valor del sistema frente a alternativas manuales.
- Documento de contribución individual inicial: marco para evaluar participación futura.

### Diego
- Preparación y estructuración de la presentación preliminar del proyecto (objetivos, motivación, propuesta de uso en entorno académico).
- Consolidación de mensajes clave para comunicar beneficios.
---
## Etapa 2: Análisis y Diseño Detallado / Arranque Técnico
Foco: transición de lo conceptual a lo técnico, esqueleto del código y bases para la implementación.

### Christian
- Construcción del esqueleto del proyecto Spring Boot (paquetes: `controller`, `service`, `repository`, `model`).
- Implementación inicial de gestión de Pacientes y Sesiones Clínicas (entidades, repositorios JPA y controladores básicos).
- Definición de DTOs iniciales para desacoplar las entidades del intercambio con el frontend.
- Ajuste de convenciones de nombres para garantizar consistencia futura (e.g. sufijos Request/Response).

### Mauricio
- Implementación adicional de lógica de negocio y soporte para despliegue de la base de datos.
- Configuración base de la conexión a la base de datos (PostgreSQL) y validaciones iniciales de integridad.
- Revisión cruzada del funcionamiento de la API y corrección de endpoints (parámetros, rutas y códigos de respuesta).

### Corrección conjunta
- Revisión grupal de flujos: alta de paciente, registro de sesión y obtención de historial.
- Depuración de errores iniciales (nulos en relaciones y validaciones de entrada).

### Diego
- Soporte en pruebas manuales tempranas de los endpoints para verificar que los casos de uso principales estaban cubiertos.
- Feedback sobre organización de paquetes y orden de carga de datos.

---
## Etapa 3: Implementación Funcional / Entrega y Documentación Final
Foco: ampliaciones funcionales, pulido, exportación y documentación pública.

### Christian 
- Implementación de exportación de expediente a PDF (integración con servicio PDF y formateo de contenido clínico).
- Desarrollo de interfaces de usuario estáticas para gestión de pacientes, sesiones, entrevista inicial y consentimiento informado.
- Integración de apartados del expediente en vistas navegables (estructuración de secciones y lógica de selección).
- Ajustes de usabilidad (mensajes de estado y manejo básico de errores en frontend).

### Mauricio
- Configuración detallada de PostgreSQL en entorno de despliegue y verificación de migraciones implícitas por JPA.
- Deploy del web service y revisión de la estabilidad tras incorporación de nuevas entidades.
- Modificaciones iterativas de la interfaz para mejorar navegación (cambios de disposición vertical/horizontal y revert de decisiones según feedback).
- Documentación formal de la API con OpenAPI/Swagger (anotaciones, configuración y preparación para publicación externa).

### Diego
- Ajustes adicionales en la interfaz: refinamiento de layouts y agrupación de secciones del expediente.
- Generación de datos de prueba para validar flujos completos (pacientes, sesiones, entrevista y consentimiento).
- Reorganización del repositorio para claridad (separación de dominios, limpieza de archivos redundantes y orden en `static/`).
- Extensión de funcionalidades: incorporación de nuevos apartados al expediente e iteraciones sobre lógica de guardado/upsert.

### Trabajo conjunto final
- Revisión de presentación (diapositivas y video) con demostración de flujo integral.
- Ajuste de documentación para coherencia (nombres de endpoints y tags en Swagger).




## Distribución de Contribución por Etapa (100% cada etapa entre integrantes activos)

### Etapa 1 (Descubrimiento y Alcance)
| Persona   | % Etapa 1 | Evidencias clave |
|-----------|-----------|------------------|
| Christian | 40%       | Organización docs, RNF, UML, Casos de Uso |
| Mauricio  | 40%       | RF, Historias Usuario, Factibilidad, Alcances, Prioridades |
| Diego     | 20%       | Presentación inicial, definición narrativa proyecto |

### Etapa 2 (Análisis / Diseño Técnico / Arranque)
| Persona   | % Etapa 2 | Evidencias clave |
|-----------|-----------|------------------|
| Christian | 40%       | Esqueleto backend, pacientes/sesiones, DTOs iniciales |
| Mauricio  | 40%       | Lógica adicional, conexión BD, revisión endpoints |
| Diego     | 20%       | Pruebas manuales, feedback estructura paquetes |

### Etapa 3 (Implementación Funcional / Entrega)
| Persona   | % Etapa 3 | Evidencias clave |
|-----------|-----------|------------------|
| Christian | 35%       | PDF expediente, UI secciones, integración apartados |
| Mauricio  | 35%       | Configuración PostgreSQL, deploy, documentación API, refactor UI |
| Diego     | 30%       | Datos prueba, reorganización repo, nuevas funcionalidades, refinamiento UI |

### Promedio de contribución general del proyecto (3 etapas)
| Persona   | % |
|-----------|-----------|
| Christian | 38%       |
| Mauricio  | 38%       | 
| Diego     | 24%       | 




[Volver al inicio](../../README.md)
