# QuizMaker

**Versión 1.3**
## Descripción

QuizMaker es una aplicación desarrollada en Java que permite extraer preguntas y respuestas de documentos de texto en formato .docx y generar hojas de cálculo en formato Excel. Estas hojas pueden importarse posteriormente a Google Forms para crear tests automatizados, facilitando su implementación en plataformas como WordPress.​
## Estado del Proyecto

**:construction:** Proyecto en desarrollo :construction:​
## Características
# QuizMaker

**Versión 1.2 - Proyecto en desarrollo**

QuizMaker es una aplicación de escritorio desarrollada en **Java 21** con interfaz gráfica mediante **Swing**. Su objetivo principal es facilitar la creación de tests automatizados a partir de archivos `.docx`, extrayendo las preguntas contenidas y exportándolas a un archivo `.xlsx` listo para ser usado en plataformas como **Google Forms** o integrarse en sitios web (por ejemplo, mediante WordPress).

## Características

- Interfaz amigable con **Swing** (sin consola).
- Lectura y análisis automático de archivos `.docx`.
- Exportación directa de preguntas a archivos `.xlsx`.
- Generación de ejecutables `.exe` mediante **Launch4j**.
- Soporte actual para español (preguntas tipo `Pregunta 1:` o `1.`).
- Proyecto modular y documentado con **Javadoc**.
- Preparado para futuras integraciones con **Google Forms API**.

## Tecnologías Usadas

- **Java 21**
- **Apache Maven**
- **Swing (GUI)**
- **Apache POI** (lectura `.docx` y generación `.xlsx`)
- **Launch4j** (generación del ejecutable)
- **Google API Client** *(preparado para integración futura con Forms)*

## Requisitos Previos

- JDK 21 o superior.
- Maven instalado y configurado.
- Sistema operativo Windows (para `.exe` generado con Launch4j).

## Instalación y Uso

### Clonar el repositorio
```bash
git clone https://github.com/TonyArPe/QuizMaker.git
cd QuizMaker/app-quiz
```

### Generar el `.jar` ejecutable con dependencias
```bash
mvn clean package
```

El archivo generado se encontrará en `target/demo-1.0-jar-with-dependencies.jar`.

### Crear el ejecutable `.exe`
Usa **Launch4j** y configura como ruta del `.jar` el archivo generado. Establece la clase principal en:
```
com.example.quiz.QuizAppGUI
```

## Cómo usar la aplicación

1. Ejecuta `QuizMaker.exe` o el `.jar`.
2. Selecciona un archivo `.docx` con preguntas.
3. Las preguntas serán analizadas y exportadas automáticamente como `Preguntas.xlsx` en tu Escritorio.
4. Ese archivo es compatible con **Google Forms**.

## Estructura del Proyecto

- `src/main/java/com/example/quiz`
  - `QuizAppGUI.java` → Interfaz gráfica.
  - `MainApp.java` → Versión por consola (legacy).
  - `FileSelector.java` → Lógica para lectura y análisis de `.docx`.
  - `ExcelCreator.java` → Lógica para exportación a Excel.

## Notas Adicionales

- El análisis de preguntas está basado en expresiones regulares que detectan encabezados del tipo `1. Pregunta` o `Pregunta 1`.
- Próximamente se integrará la subida automática de las preguntas a Google Forms.
- Se recomienda cerrar el Excel de salida antes de generar uno nuevo para evitar errores de acceso denegado.

---

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