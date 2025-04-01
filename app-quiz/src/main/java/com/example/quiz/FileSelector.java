package com.example.quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSelector {

    private static final String DOCS_FOLDER = "docs";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File docsDirectory = new File(DOCS_FOLDER);

        // Verificar si la carpeta 'docs' existe
        if (!docsDirectory.exists() || !docsDirectory.isDirectory()) {
            System.out.println("La carpeta '" + DOCS_FOLDER + "' no existe. Por favor, créala y coloca los archivos a analizar en ella.");
            scanner.close();
            return;
        }

        // Solicitar al usuario que ingrese el nombre del archivo
        try {
            File selectedFile = null;
        while (selectedFile == null) {
            System.out.println("\nPor favor, introduce el nombre del archivo que deseas analizar (incluyendo la extensión, por ejemplo, 'archivo.docx'):");
            String fileName = scanner.nextLine().trim();
            selectedFile = new File(docsDirectory, fileName);

            if (!selectedFile.exists() || !selectedFile.isFile()) {
                System.out.println("El archivo '" + fileName + "' no se ha encontrado en la carpeta '" + DOCS_FOLDER + "'. Inténtalo de nuevo.");
                selectedFile = null;
            }
        }

        scanner.close();

        // Aquí puedes llamar a tu método de análisis, por ejemplo:
        analyzeFile(selectedFile);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
}
private static void analyzeFile(File file) {
    // Analizar los archivos .docx
    System.out.println("Analizando el archivo: " + file.getAbsolutePath());
    List<String> questions = new ArrayList<>();
    
}
}
