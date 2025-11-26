# Módulo - Sistema de Expedientes Digitales

## Documentación

1. **Introducción**
    - [Objetivo](docs/objetivo.md)
    - [Alcances](docs/alcances.md)
    - [Limitaciones](docs/limitaciones.md)
2. **[Propuesta de valor](docs/propuesta_valor.md)**
3. **Requisitos**
    - [Requisitos Funcionales (RF)](docs/requisitos_funcionales.md)
    - [Requisitos No Funcionales (RNF)](docs/requisitos_no_funcionales.md)
4. **Priorización**
    - [Método de prioridad](docs/metodos_prioridad.md)
    - [Requisitos/Historias de Usuario](docs/requisitos.md)
    - [Análisis de factibilidad](docs/analisis_factibilidad.md)
5. **Artefactos**
    - [Vista previa Casos de uso](docs/diagramas/diagrama_casos.png)
    - [Historias de usuario](docs/historias_usuario.md)
6. **Procesos de abstracción**
    - [Estructura de clases](docs/estructura_clases.md)
    - [Vista previa UML](docs/diagramas/diagrama_uml_preview.png)
7. **Procesos del proyecto**
    - [Descripción](docs/descripcion_proceso.md)
    - [Gestión](docs/gestion_proceso.md)
    - [Contribuciones individuales](docs/contribucion_individual.md)
8. **Presentación**

    [![Video](https://img.youtube.com/vi/GppRmBpD5Mc/0.jpg)](https://www.youtube.com/watch?v=GppRmBpD5Mc "Expedientes clínicos")
   - [Powerpoint](docs/ExpedientesClinicos.pdf)

    **Entregas**
   - [Primer entrega](https://github.com/Diego-Ceron/ExpedientesClinicos/tree/Primer-entrega)
 - [Segunda entrega](https://github.com/Diego-Ceron/ExpedientesClinicos/tree/Segunda-entrega)
 
## Estructura del repositorio

A continuación se describe brevemente qué contiene cada carpeta principal y su función dentro del proyecto.

- **`app/`**: Código fuente de la aplicación.
    - **`app/backend/`**: Backend Java (Spring Boot) del sistema.
        - `pom.xml`: Dependencias y configuración Maven del módulo backend.
        - **`src/main/`**: Código fuente Java del backend.
            - **`java/com/expedientesclinicos/`**:
                - `config/`: Configura las reglas CORS para la aplicación
                - `controller/`: Controladores REST.
                - `dto/`: Objetos de transferencia (requests, responses y resúmenes para la API).
                - `exception/`: Excepciones personalizadas y manejadores de errores.
                - `model/paciente`: Entidades JPA que representan tablas en la base de datos.
                - `repository/paciente`: Repositorios Spring Data JPA para acceso a la base de datos.
                - `service/`: Lógica de negocio y servicios para operar con entidades.
                - `ExpedientesClinicosApplication.java`
            - **`/resources/`**:
                - `static/`: Páginas html estáticas para la interfaz web.
                - `application.properties`: Configuración de Spring Boot y datasource.
    - `Dockerfile`: 
    - `pom.xml`:
- **`docs/`**: Documentación del proyecto (alcances, requisitos, diagramas, historias de usuario, entregables y archivos de presentación).

