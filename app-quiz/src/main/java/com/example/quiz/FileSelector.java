package com.example.quiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * Clase que gestiona la selección y análisis de archivos Word para extraer preguntas de exámenes.
 * 
 * @author TonyArPe
 * @version 1.1
 * @since 01/04/2025
 */
public class FileSelector {

    /**
     * Solicita al usuario que seleccione un archivo .docx de la carpeta especificada.
     * 
     * @param carpetaDocumentos Ruta de la carpeta que contiene los archivos .docx.
     * @return Archivo seleccionado por el usuario o null si no se seleccionó ninguno.
     */
    public static File seleccionarArchivo(String carpetaDocumentos) {
        Scanner scanner = new Scanner(System.in);
        File directorioDocumentos = new File(carpetaDocumentos);

        // Verifica si la carpeta existe y es un directorio
        if (!directorioDocumentos.exists() || !directorioDocumentos.isDirectory()) {
            System.out.println("La carpeta '" + carpetaDocumentos
                    + "' no existe. Por favor, créala y coloca los archivos a analizar en ella.");
            scanner.close();
            return null;
        }

        // Solicitar al usuario que ingrese el nombre del archivo
        File archivoSeleccionado = null;
        while (archivoSeleccionado == null) {
            System.out.println("\nIntroduce el nombre del archivo que deseas analizar "
                    + "(incluyendo la extensión, por ejemplo, 'archivo.docx'):");
            String nombreArchivo = scanner.nextLine().trim();
            archivoSeleccionado = new File(directorioDocumentos, nombreArchivo);

            if (!archivoSeleccionado.exists() || !archivoSeleccionado.isFile()) {
                System.out.println("El archivo '" + nombreArchivo + "' no se ha encontrado en la carpeta '"
                        + carpetaDocumentos + "'. Inténtalo de nuevo.");
                archivoSeleccionado = null;
            }
        }
        return archivoSeleccionado;
    }

    /**
     * Analiza el archivo proporcionado y extrae las preguntas de los test.
     * 
     * @param archivo Archivo .docx que será analizado.
     * @return Lista de preguntas extraídas del archivo.
     */
    public static List<String> analizarArchivo(File archivo) {
        System.out.println("Analizando el archivo: " + archivo.getAbsolutePath());
        List<String> preguntas = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(archivo);
             XWPFDocument documento = new XWPFDocument(fis)) {

            List<XWPFParagraph> parrafos = documento.getParagraphs();
            StringBuilder preguntaActual = new StringBuilder();

            for (XWPFParagraph parrafo : parrafos) {
                String texto = parrafo.getText().trim();
                // Detectar el inicio de una pregunta
                if (texto.matches("^\\d+\\.\\s.*") || texto.toLowerCase().startsWith("pregunta ")) {
                    if (preguntaActual.length() > 0) {
                        preguntas.add(preguntaActual.toString());
                        preguntaActual.setLength(0);
                    }
                    preguntaActual.append(texto).append("\n");
                } else if (!texto.isEmpty()) {
                    preguntaActual.append(texto).append("\n");
                }
            }
            // Añadir la última pregunta si existe
            if (preguntaActual.length() > 0) {
                preguntas.add(preguntaActual.toString());
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return preguntas;
    }

    /**
     * Muestra las preguntas extraídas del archivo.
     * 
     * @param preguntas Lista de preguntas extraídas.
     */
    public static void mostrarPreguntas(List<String> preguntas) {
        if (preguntas.isEmpty()) {
            System.out.println("No se encontraron preguntas en el archivo.");
        } else {
            System.out.println("Preguntas encontradas en el archivo:");
            for (String pregunta : preguntas) {
                System.out.println(pregunta);
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
