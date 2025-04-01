package com.example.quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.*;

public class FileSelector {

    private static final String DOCS_FOLDER = "app-quiz\\src\\docs";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File docsDirectory = new File(DOCS_FOLDER);

        // Verificar si la carpeta 'docs' existe
        if (!docsDirectory.exists() || !docsDirectory.isDirectory()) {
            System.out.println("La carpeta '" + DOCS_FOLDER
                    + "' no existe. Por favor, créala y coloca los archivos a analizar en ella.");
            scanner.close();
            return;
        }

        // Solicitar al usuario que ingrese el nombre del archivo
        File selectedFile = null;
        while (selectedFile == null) {
            System.out.println(
                    "\nPor favor, introduce el nombre del archivo que deseas analizar (incluyendo la extensión, por ejemplo, 'archivo.docx'):");
            String fileName = scanner.nextLine().trim();
            selectedFile = new File(docsDirectory, fileName);

            if (!selectedFile.exists() || !selectedFile.isFile()) {
                System.out.println("El archivo '" + fileName + "' no se ha encontrado en la carpeta '" + DOCS_FOLDER
                        + "'. Inténtalo de nuevo.");
                selectedFile = null;
            }
        }

        scanner.close();

        // Llamar al método de análisis
        analyzeFile(selectedFile);
    }

    private static void analyzeFile(File file) {
        System.out.println("Analizando el archivo: " + file.getAbsolutePath());
        List<String> questions = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            StringBuilder currentQuestion = new StringBuilder();

            for (XWPFParagraph para : paragraphs) {
                String text = para.getText().trim();
                // Detectar el inicio de una pregunta
                if (text.matches("^\\d+\\.\\s.*") || text.toLowerCase().startsWith("pregunta ")) {
                    if (currentQuestion.length() > 0) {
                        questions.add(currentQuestion.toString());
                        currentQuestion.setLength(0);
                    }
                    currentQuestion.append(text).append("\n");
                } else if (!text.isEmpty()) {
                    currentQuestion.append(text).append("\n");
                }
            }
            // Añadir la última pregunta si existe
            if (currentQuestion.length() > 0) {
                questions.add(currentQuestion.toString());
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Mostrar las preguntas extraídas
        if (questions.isEmpty()) {
            System.out.println("No se encontraron preguntas en el archivo.");
        } else {
            System.out.println("Preguntas encontradas en el archivo:");
            for (String question : questions) {
                System.out.println(question);
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
