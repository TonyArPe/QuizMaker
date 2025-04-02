package com.example.quiz;

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
     * @return 
     */
    public static void exportExcel(List<String> questions, String filepath){
        // Crear un nuevo libro de trabajo de Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Crear una nueva hoja en el libro de trabajo
        XSSFSheet sheet = workbook.createSheet("Preguntas");

        // Iterar sobre la lista de preguntas y agregarlas a la hoja
        int rowNum = 0;
        for(String question : questions){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(question);
        }

        // Escribir el libro de trabajo en el archivo especificado
        try (FileOutputStream escrituraFile = new FileOutputStream(filepath)) {
            workbook.write(escrituraFile);
            System.out.println("Archivo Excel creado exitosamente en: " + filepath);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo Excel: " + e.getMessage());
        } finally {
            // Cerrar el libro de trabajo para liberar recursos y evitar fugas
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo workbook: " + e.getMessage());
            }
        }
    }
}
