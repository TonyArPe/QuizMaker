# QuizMaker

**Versión 1.0**

## Descripción

QuizMaker es una aplicación desarrollada en Java que permite extraer preguntas y respuestas de documentos de texto y generar hojas de cálculo. Posteriormente, estas hojas pueden importarse a Google Forms para crear tests automatizados, facilitando su implementación en plataformas como WordPress.

## Estado del Proyecto

:construction: Proyecto en desarrollo :construction:

## Características

- **Extracción Automática:** Analiza documentos `.docx` para identificar preguntas y respuestas.
- **Generación de Hojas de Cálculo:** Crea archivos compatibles con Google Forms.
- **Integración con Google Forms:** Facilita la creación de tests en línea a partir de documentos existentes.

## Instalación

### Clonar el Repositorio:
```bash
git clone https://github.com/TonyArPe/QuizMaker.git
 ```

### Navegar al Directorio del Proyecto:
```bash
cd QuizMaker
```

### Compilar el Proyecto con Maven:
```bash
mvn clean install
```

### Uso

- **Ejecutar la Aplicación:**
```bash
java -jar target/QuizMaker.jar
```

- **Seleccionar el Documento de Texto:** Sigue las instrucciones en pantalla para elegir el archivo .docx que deseas procesar.

- **Generar la Hoja de Cálculo:** La aplicación creará automáticamente un archivo compatible con Google Forms en el directorio especificado.

### Tecnologías Utilizadas

- **Java 21:** Lenguaje de programación principal.

- **Apache POI:** Biblioteca para manipulación de documentos de Microsoft Office.

- **Javadoc:** Para la documentacion del codigo

- **Google Forms API:** Para la integración y creación automatizada de formularios.

### Contribución

deseas contribuir al desarrollo de QuizMaker:

- **Fork del Repositorio.**

- **Crear una Nueva Rama:**
```bash
git checkout -b feature/NuevaCaracteristica
```

- **Realizar Cambios y Confirmar:**
```bash
git commit -m "Descripción de la nueva característica"
```

- **Enviar los Cambios:**
```bash
git push origin feature/NuevaCaracteristica
```

- **Abrir una Pull Request.**

- **Ejecuta la Clase de Prueba**
```bash
mvn exec:java -Dexec.mainClass="com.example.quiz.WordDocumentTest"
```

### Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo **LICENSE** para más detalles.
### Autores

**TonyArPe:** Desarrollador principal y creador del proyecto.
