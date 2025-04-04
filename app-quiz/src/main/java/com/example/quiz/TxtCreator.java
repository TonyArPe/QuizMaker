package com.example.quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que exporta preguntas a un archivo .txt en formato Aiken compatible con
 * Moodle.
 * Cada entrada en la lista debe seguir la estructura del formato Aiken.
 * @ author TonyArPe
 * @ version 1.4
 * @ since 04/04/2025
 **/
public class TxtCreator {
    /**
     * Exporta las preguntas proporcionadas al formato Aiken en un archivo .txt.
     * 
     * @param questions Lista de preguntas con respuestas en formato Aiken.
     * @param filePath  Ruta donde se guardara el archivo .txt.
     */
    public static void exportToAikenFormat(List<String> questions, String filePath) {
        File archivoSalida = new File(filePath);
        File parentDir = archivoSalida.getParentFile();

        // Si no existe el directorio lo creamos
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
            System.err.println("No se pudo crear el directorio: " + parentDir.getAbsolutePath());
            return;
        }

        // Escribir las preguntas en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (String question : questions) {
                writer.write(question);
                writer.newLine();
                writer.newLine(); // Añadir una línea en blanco entre preguntas
            }
            System.out.println("Archivo exportado correctamente a: " + archivoSalida.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
