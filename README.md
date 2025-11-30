# Módulo - Sistema de Expedientes Digitales

En este repositorio se encuentra el sistema de expedientes clínicos de la facultad de psicología. El web service de la app funciona con render [aquí](https://expedientesclinicos.onrender.com)

## Introducción

Información acerca de la planeación e impacto del proyecto.

### Objetivo
>[Archivo](docs/objetivo.md)
>Info

### Alcances
>[Archivo](docs/alcances.md)
>Info

### Limitaciones
>[Archivo](docs/limitaciones.md)
>Info

## Propuesta de valor

>[Archivo](docs/propuesta_valor.md)

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
    - `Dockerfile`: Compila la aplicación con Maven y crea una imagen de ejecución que contiene el JAR final.
    - `pom.xml`: Define el artefacto, dependencias y el plugin de Spring Boot
- **`docs/`**: Documentación del proyecto (alcances, requisitos, diagramas, historias de usuario, entregables y archivos de presentación).

## Documentación

3. **Requisitos**
    - [Requisitos Funcionales (RF)](docs/requisitos/requisitos_funcionales.md)
    - [Requisitos No Funcionales (RNF)](docs/requisitos/requisitos_no_funcionales.md)
4. **Priorización**
    - [Método de prioridad](docs/priorizacion/metodos_prioridad.md)
    - [Requisitos/Historias de Usuario](docs/priorizacion/requisitos/requisitos.md)
    - [Análisis de factibilidad](docs/priorizacion/analisis_factibilidad.md)
5. **Artefactos**
    - [Vista previa Casos de uso](docs/artefactos/diagrama_casos.png)
    - [Historias de usuario](docs/artefactos/historias_usuario.md)
6. **Procesos de abstracción**
    - [Estructura de clases](docs/artefactos/estructura_clases.md)
    - [Vista previa UML](docs/artefactos/diagrama_uml_preview.png)
7. **Procesos del proyecto**
    - [Descripción](docs/proceso-proyecto/descripcion_proceso.md)
    - [Gestión](docs/proceso-proyecto/gestion_proceso.md)
    - [Contribuciones individuales](docs/proceso-proyecto/contribucion_individual.md)
8. **Presentación**

    [![Video](https://img.youtube.com/vi/GppRmBpD5Mc/0.jpg)](https://www.youtube.com/watch?v=GppRmBpD5Mc "Expedientes clínicos")
   - [Powerpoint](docs/presentacion/ExpedientesClinicos.pdf)

    **Entregas**
    - [Primer entrega](https://github.com/Diego-Ceron/ExpedientesClinicos/tree/Primer-entrega)
    - [Segunda entrega](https://github.com/Diego-Ceron/ExpedientesClinicos/tree/Segunda-entrega)