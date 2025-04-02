package com.example.quiz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase que se encarga de crear un archivo Excel a partir de una lista de preguntas.
 * Utiliza Apache POI para la manipulación de archivos Excel.
 * 
 * @author TonyArPe
 * @version 1.1
 * @since 02/04/2025
 */
public class ExcelCreator {
     /**
     * Exporta una lista de preguntas a un archivo Excel en la ruta especificada.
     * 
     * @param questions Lista de preguntas a incluir en el archivo Excel.
     * @param filePath  Ruta donde se guardará el archivo Excel.
     */
    public static void exportExcel(List<String> questions, String filepath) {
        File archivoSalida = new File(filepath);  // Mover esta línea arriba
    
        // Crear el directorio de salida si no existe
        File directorioSalida = archivoSalida.getParentFile();
        if (directorioSalida != null && !directorioSalida.exists()) {
            if (directorioSalida.mkdirs()) {
                System.out.println("Directorio de salida creado: " + directorioSalida.getAbsolutePath());
            } else {
                System.err.println("No se pudo crear el directorio de salida: " + directorioSalida.getAbsolutePath());
                return;
            }
        }

        if (archivoSalida.exists()) {
            if (!archivoSalida.delete()) {
                System.err.println("No se pudo eliminar el archivo existente: " + archivoSalida.getAbsolutePath());
                return;
            }
        }        
    
        // Usar try-with-resources para manejar workbook y escritura
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream escrituraFile = new FileOutputStream(archivoSalida)) {
    
            XSSFSheet sheet = workbook.createSheet("Preguntas");
    
            int rowNum = 0;
            for (String question : questions) {
                Row row = sheet.createRow(rowNum++);
                Cell cell = row.createCell(0);
                cell.setCellValue(question);
            }
    
            workbook.write(escrituraFile);
            System.out.println("Archivo Excel creado exitosamente en: " + archivoSalida.getAbsolutePath());
    
        } catch (IOException e) {
            System.err.println("Error al crear el archivo Excel: " + e.getMessage());
        }
    }    
}    
