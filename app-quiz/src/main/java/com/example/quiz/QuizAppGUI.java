package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Arrays;

public class QuizAppGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    // Componentes de la interfaz
    private JTextField fileNameField;
    private JTextArea outputArea;
    private JProgressBar progressBar;

    public QuizAppGUI() {
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Crear y configurar los componentes
        initializeComponents();

        // Hacer visible la ventana
        setVisible(true);
    }

    private void initializeComponents() {
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Panel superior para la selección de archivo
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel instructionLabel = new JLabel("Seleccione el archivo de preguntas:");
        fileNameField = new JTextField(30);
        JButton selectFileButton = new JButton("Seleccionar Archivo");
        topPanel.add(instructionLabel);
        topPanel.add(fileNameField);
        topPanel.add(selectFileButton);

        // Área de texto para mostrar mensajes o resultados
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Agregar componentes al panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.SOUTH);

        // Agregar panel principal al frame
        add(mainPanel);

        // Acción del botón para seleccionar archivo
        selectFileButton.addActionListener(createFileSelectionListener());
    }

    private ActionListener createFileSelectionListener() {
        return e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileNameField.setText(selectedFile.getAbsolutePath());
                outputArea.append("Archivo seleccionado: " + selectedFile.getAbsolutePath() + "\n");

                // Procesar el archivo seleccionado
                processSelectedFile(selectedFile);
            }
        };
    }

    private void processSelectedFile(File file) {
        // Validar que el archivo existe y es legible
        if (!file.exists() || !file.canRead()) {
            outputArea.append("Error: No se puede leer el archivo seleccionado.\n");
            return;
        }

        // Simulación de lectura y extracción de preguntas del archivo
        List<String> questions = Arrays.asList("Pregunta 1", "Pregunta 2", "Pregunta 3"); // Ejemplo de preguntas

        // Actualizar la barra de progreso
        progressBar.setIndeterminate(true);

        // Exportar las preguntas a un archivo Excel
        exportQuestionsToExcel(questions);

        // Detener la barra de progreso
        progressBar.setIndeterminate(false);
    }

    private void exportQuestionsToExcel(List<String> questions) {
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";
        try {
            ExcelCreator.exportExcel(questions, desktopPath);
            outputArea.append("Archivo Excel creado en: " + desktopPath + "\n");
        } catch (Exception e) {
            outputArea.append("Error al crear el archivo Excel: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
