package com.example.quiz;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que coordina la selección de archivos, el análisis de preguntas
 * y la exportación de estas a un archivo Excel.
 * 
 * <p>Esta clase permite al usuario seleccionar un archivo .docx desde una carpeta
 * específica, analiza el contenido del archivo para extraer preguntas y luego
 * exporta estas preguntas a un archivo Excel en una ubicación determinada.</p>
 * 
 * <p><strong>Nota:</strong> Asegúrese de que las rutas de las carpetas y archivos
 * especificados en las constantes {@code DOCS_FOLDER} y {@code OUTPUT_EXCEL_PATH}
 * existan y sean correctas.</p>
 * 
 * @author TonyArPe
 * @version 1.0
 * @since 02/04/2025
 */
public class MainApp {

    /**
     * Ruta de la carpeta que contiene los archivos .docx a analizar.
     */
    private static final String DOCS_FOLDER = "app-quiz\\src\\docxFiles";

    /**
     * Ruta completa donde se guardará el archivo Excel generado con las preguntas.
     */
    private static final String OUTPUT_EXCEL_PATH = "app-quiz\\src\\docsFiles\\Excels\\Preguntas.xlsx";

    /**
     * Método principal que inicia la aplicación.
     * 
     * <p>Este método realiza las siguientes acciones:</p>
     * <ol>
     *   <li>Verifica la existencia de la carpeta de documentos.</li>
     *   <li>Solicita al usuario que introduzca el nombre del archivo a analizar.</li>
     *   <li>Analiza el archivo seleccionado para extraer las preguntas.</li>
     *   <li>Si se encuentran preguntas, las muestra y las exporta a un archivo Excel.</li>
     * </ol>
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File docsDirectory = new File(DOCS_FOLDER);

        // Verifica si la carpeta docxFiles existe y es un directorio
        if(!docsDirectory.exists() || !docsDirectory.isDirectory()) {
            System.out.println("La carpeta '" + DOCS_FOLDER
                    + "' no existe. Por favor, créala y coloca los archivos a analizar en ella.");
            scanner.close();
            return;
        }

        // Solicitar al usuario que ingrese el nombre del archivo
        File selectedFile = null;
        while(selectedFile == null){
            System.out.println("/n Introduce el nombre del arcvhivo que deseas analizar "+
            "(incluyendo la extensión, por ejemplo, 'archivo.docx'):");
            String fileName = scanner.nextLine().trim().toLowerCase();
            selectedFile = new File(docsDirectory, fileName);

            if(!selectedFile.exists() || !selectedFile.isFile()){
                System.out.println("El archivo '" + fileName + "' no se ha encontrado en la carpeta '" + DOCS_FOLDER
                        + "'. Inténtalo de nuevo.");
                selectedFile = null;
            }
        }
        // Analizar el archivo seleccionado paa extraer las preguntas
        List<String> questions = FileSelector.analyzeFile(selectedFile);

        // Verificar si habia preguntas
        if(questions.isEmpty()){
            System.out.println("No se encontraron preguntas en el archivo seleccionado.");
        } else {
            // Mostrar las preguntas
            FileSelector.mostrarPreguntas(questions);

            // Verificar si la carpeta de salida existe, si no, crearla
            File carpetaSalida = new File(OUTPUT_EXCEL_PATH);
            if(!carpetaSalida.exists()){
                carpetaSalida.mkdirs();
                System.out.println("La carpeta de salida '" + OUTPUT_EXCEL_PATH + "' no existía y ha sido creada.");
            }

            // Exportar las preguntas al Excel
            ExcelCreator.exportExcel(questions, OUTPUT_EXCEL_PATH);
            System.out.println("Preguntas exportadas exitosamente a " + OUTPUT_EXCEL_PATH);
        }
        scanner.close();
    }  
}
