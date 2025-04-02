package com.example.quiz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase que se encarga de crear un archivo Excel a partir de una lista de preguntas.
 * @author TonyArPe
 * @version 1.0
 * @since 02/04/2025
 * @param questions Lista de preguntas a incluir en el archivo Excel.
 * @param filepath Ruta del archivo Excel a crear.
 */
public class ExcelCreator {
    public static void exportExcel(List<String> questions, String filepath){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Preguntas");

        int rowNum = 0;
        for(String question : questions){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(question);
        }

        try (FileOutputStream escrituraFile = new FileOutputStream(filepath)) {
            workbook.write(escrituraFile);
            System.out.println("Archivo Excel creado exitosamente en: " + filepath);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo Excel: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo workbook: " + e.getMessage());
            }
        }
    }
}
