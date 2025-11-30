# Análisis de factibilidad
Se realizó un análisis de los requerimientos del sistema de expedientes clínicos con el objetivo de determinar su importancia para el cumplimiento del propósito del sistema y su factibilidad en términos técnicos y de tiempo.
## Requerimientos funcionales
Alta importancia y alta factibilidad (Must Have): Incluyen la creación, edición y eliminación de expedientes (RF-01), búsqueda de expedientes (RF-02), consulta y edición de pacientes asignados (RF-03), control de sesiones (RF-04), edición de hojas de sesión (RF-05) y restricción de acceso a la información (RF-08).
> Estos requerimientos son críticos para garantizar que el sistema cumpla con su propósito principal de digitalizar y centralizar la información clínica. Además, son viables con herramientas de desarrollo web y bases de datos relacionales.
Alta importancia pero factibilidad media (Should Have): Incluyen la subida de archivos (RF-06) y la exportación digital de apartados del expediente (RF-07).
> Son importantes para mejorar la eficiencia y reducir el uso de documentos físicos, pero su implementación puede requerir mayor inversión en almacenamiento, conversión de formatos y usabilidad de la interfaz.
## Requerimientos no funcionales
Alta importancia y factibilidad (Must Have): Incluyen seguridad en el manejo de datos (RNF-06), disponibilidad y confiabilidad (RNF-10), compatibilidad con navegadores (RNF-08), facilidad de aprendizaje (RNF-02), minimización de errores (RNF-04) y tiempo de consulta razonable (RNF-01).
> Son esenciales debido al carácter sensible de la información clínica y la necesidad de que los terapeutas puedan adoptar rápidamente el sistema.
Alta importancia y factibilidad media (Should Have): Rendimiento y escalabilidad (RNF-07), organización clara de la información (RNF-03), consistencia de interfaz (RNF-05) y mantenibilidad (RNF-09).
> Aportan significativamente a la experiencia de usuario y a la durabilidad del sistema, aunque su implementación puede requerir más planeación en diseño de arquitectura y pruebas de usabilidad.
## Historias de usuario
Críticas y factibles (Must Have): Creación/edición de expedientes, registro/edición de sesiones, consulta de historial, asignación de pacientes, creación de cuentas y definición de roles.
> Son totalmente factibles con un diseño de base de datos bien estructurado y mecanismos de autenticación por roles.
Importantes pero de implementación gradual (Should Have): Exportación de historiales en PDF, generación de reportes y consultas avanzadas.
> Aumentan la eficiencia administrativa, pero pueden incorporarse en fases posteriores.
Deseables (Could Have): Alertas automáticas y recordatorios.
> Aunque agregan valor al sistema, no son esenciales para la operación inicial y se recomienda desarrollarlos en versiones futuras.

## Análisis
El sistema es factible en su desarrollo técnico y operativo. La mayoría de los requerimientos críticos (Must Have) tienen una alta factibilidad de implementación y garantizan el cumplimiento del propósito principal: facilitar el control digital de expedientes clínicos y sesiones, reduciendo la dependencia de registros físicos.

---
[Volver al inicio](../../README.md)
