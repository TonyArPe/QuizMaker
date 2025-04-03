package com.example.quiz;

import java.io.File;
import java.util.List;

/**
 * Clase principal que coordina la selección de archivos, el análisis de preguntas
 * y la exportación de estas a un archivo Excel.
 * 
 * @author TonyArPe
 * @version 1.1
 * @since 02/04/2025
 */
public class MainApp {

    /**
     * Ruta de la carpeta que contiene los archivos .docx a analizar.
     */
    private static final String DOCS_FOLDER = "app-quiz/src/docxFiles";

    /**
     * Ruta de la carpeta donde se guardará el archivo Excel generado con las preguntas.
     */
    private static final String OUTPUT_FOLDER = "app-quiz/src/docxFiles/Excels";

    /**
     * Nombre del archivo Excel que se generará.
     */
    private static final String OUTPUT_EXCEL_FILE = "Preguntas.xlsx";

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        // Seleccionar el archivo .docx
        File archivoSeleccionado = FileSelector.seleccionarArchivo(DOCS_FOLDER);
        if (archivoSeleccionado == null) {
            System.out.println("No se seleccionó ningún archivo.");
            return;
        }

        // Analizar el archivo seleccionado para extraer las preguntas
        List<String> preguntas = FileSelector.analizarArchivo(archivoSeleccionado);

        // Mostrar las preguntas extraídas
        FileSelector.mostrarPreguntas(preguntas);

        if (!preguntas.isEmpty()) {
            // Crear el directorio de salida si no existe
            File directorioSalida = new File(OUTPUT_FOLDER);
            if (!directorioSalida.exists()) {
                if (directorioSalida.mkdirs()) {
                    System.out.println("Directorio de salida creado: " + directorioSalida.getAbsolutePath());
                } else {
                    System.err.println("No se pudo crear el directorio de salida: " + directorioSalida.getAbsolutePath());
                    return;
                }
            }

            // Ruta completa del archivo Excel de salida
            String rutaCompletaExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";

            // Exportar las preguntas al archivo Excel
            ExcelCreator.exportExcel(preguntas, rutaCompletaExcel);
        }
    }
}
