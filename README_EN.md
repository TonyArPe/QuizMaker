# QuizMaker

**Version 1.3 - Project under development**

QuizMaker is a desktop application developed in **Java 21** with a graphical interface built using **Swing**. Its main goal is to automate the creation of tests from `.docx` files by extracting questions and exporting them into an `.xlsx` file ready to be imported into platforms like **Google Forms** or integrated into websites (e.g., via WordPress).

## Features

- Friendly GUI built with **Swing** (no console).
- Automatic reading and analysis of `.docx` files.
- Direct export of questions to `.xlsx` format.
- `.exe` generation with **Launch4j**.
- Spanish support for pattern like `Pregunta 1:` or `1.`.
- Modular project with **Javadoc** documentation.
- Ready for **Google Forms API** integration.

## Technologies Used

- **Java 21**
- **Apache Maven**
- **Swing (GUI)**
- **Apache POI** (read `.docx` and generate `.xlsx`)
- **Launch4j** (create the executable)
- **Google API Client** *(ready for future use with Forms)*

## Prerequisites

- JDK 21 or later
- Maven installed and configured
- Windows OS (for `.exe` generated with Launch4j)

## Installation & Usage

### Clone the repository
```bash
git clone https://github.com/TonyArPe/QuizMaker.git
cd QuizMaker/app-quiz
```

### Generate the executable `.jar` with dependencies
```bash
mvn clean package
```

The generated file will be found in `target/demo-1.0-jar-with-dependencies.jar`.

### Create the `.exe`
Use **Launch4j** and set the `.jar` file path. Main class to set:
```
com.example.quiz.QuizAppGUI
```

## How to Use the Application

1. Run `QuizMaker.exe` or the `.jar`.
2. Select a `.docx` file with questions.
3. The questions will be analyzed and exported as `Preguntas.xlsx` on your Desktop.
4. This file is compatible with **Google Forms**.

## Project Structure

- `src/main/java/com/example/quiz`
  - `QuizAppGUI.java` → Graphical interface.
  - `MainApp.java` → Console version (legacy).
  - `FileSelector.java` → Logic to read and analyze `.docx` files.
  - `ExcelCreator.java` → Excel export logic.

## Additional Notes

- The question analysis is based on regular expressions that detect headers such as `1. Question` or `Question 1`.
- Automatic integration with Google Forms is coming soon.
- It is recommended to close the output Excel file before generating a new one to avoid "access denied" errors.

---
