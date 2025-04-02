# QuizMaker

**Versión 1.1**
## Descripción

QuizMaker es una aplicación desarrollada en Java que permite extraer preguntas y respuestas de documentos de texto en formato .docx y generar hojas de cálculo en formato Excel. Estas hojas pueden importarse posteriormente a Google Forms para crear tests automatizados, facilitando su implementación en plataformas como WordPress.​
## Estado del Proyecto

**:construction:** Proyecto en desarrollo :construction:​
## Características

- **Interfaz Gráfica Intuitiva:** Permite a los usuarios seleccionar archivos .docx y generar archivos Excel de manera sencilla.​

- **Extracción Automática:** Analiza documentos .docx para identificar preguntas y respuestas.​

- **Generación de Hojas de Cálculo:** Crea archivos Excel compatibles con Google Forms.​

- **Integración con Google Forms:** Facilita la creación de tests en línea a partir de documentos existentes.​

## Instalación
### Prerrequisitos

- **Java 21** o superior.​

- **Apache Maven**​

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

## Uso
### Ejecutar la Aplicación:
```bash
java -jar target/QuizMaker.jar
```

## Pasos para Utilizar la Aplicación:

- **Seleccionar el Documento de Texto:** Al iniciar la aplicación, utiliza la interfaz gráfica para seleccionar el archivo .docx que deseas procesar.​

- **Generar la Hoja de Cálculo:** La aplicación analizará el documento y creará automáticamente un archivo Excel compatible con Google Forms en el escritorio del usuario.​

## Tecnologías Utilizadas

- **Java 21:** Lenguaje de programación principal.​

- **Apache POI:** Biblioteca para manipulación de documentos de Microsoft Office.​

- **Swing:** Biblioteca para la creación de interfaces gráficas en Java.​

- **Javadoc:** Para la documentación del código.​

- **Google Forms API:** Para la integración y creación automatizada de formularios.​
    Apache POI

## Contribución

Si deseas contribuir al desarrollo de QuizMaker:

- Fork del Repositorio.

- Crear una Nueva Rama:
```bash
git checkout -b feature/NuevaCaracteristica
```

- Realizar Cambios y Confirmar:
```bash
git commit -m "Descripción de la nueva característica"
```

- Enviar los Cambios:
```bash
git push origin feature/NuevaCaracteristica
```

- Abrir una Pull Request.

## Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo **LICENSE** para más detalles.​
## Autores

**TonyArPe:** Desarrollador principal y creador del proyecto.