package com.example.quiz;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WordDocumentTest {

    public static void main(String[] args) {
        try (XWPFDocument document = new XWPFDocument()) {

            // Título centrado
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Documento de Prueba");
            titleRun.setBold(true);
            titleRun.setFontSize(20);

            // Párrafo
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun paragraphRun = paragraph.createRun();
            paragraphRun.setText("Este es un párrafo de ejemplo en el documento de prueba.");

            // Lista de ejemplo
            String[] items = {"Elemento 1", "Elemento 2", "Elemento 3"};
            for (String item : items) {
                XWPFParagraph listItem = document.createParagraph();
                listItem.setIndentationLeft(400);
                XWPFRun listItemRun = listItem.createRun();
                listItemRun.setText("• " + item);
            }

            try (FileOutputStream out = new FileOutputStream("DocumentoPrueba.docx")) {
                document.write(out);
            }

            System.out.println("Documento de Word creado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
