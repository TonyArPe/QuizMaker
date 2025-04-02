package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Clase que proporciona una interfaz gráfica para seleccionar un archivo de preguntas,
 * analizarlo y exportar las preguntas a un archivo Excel.
 * 
 * @author TonyArPe
 * @version 1.2
 * @since 02/04/2025
 */
public class QuizAppGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField fileNameField;
    private JTextArea outputArea;
    private JProgressBar progressBar;

    /**
     * Constructor que inicializa la interfaz gráfica.
     */
    public QuizAppGUI() {
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initializeComponents();

        setVisible(true);
    }

    /**
     * Inicializa y coloca los componentes de la interfaz gráfica.
     */
    private void initializeComponents() {
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

        add(mainPanel);

        // Acción del botón para seleccionar archivo
        selectFileButton.addActionListener(e -> selectAndProcessFile());
    }

    /**
     * Abre un diálogo para seleccionar un archivo y lo procesa.
     */
    private void selectAndProcessFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileNameField.setText(selectedFile.getAbsolutePath());
            outputArea.append("Archivo seleccionado: " + selectedFile.getAbsolutePath() + "\n");

            processSelectedFile(selectedFile);
        }
    }

    /**
     * Procesa el archivo seleccionado: lo analiza y exporta las preguntas a un archivo Excel.
     * 
     * @param file Archivo seleccionado por el usuario.
     */
    private void processSelectedFile(File file) {
        if (!file.exists() || !file.canRead()) {
            outputArea.append("Error: No se puede leer el archivo seleccionado.\n");
            return;
        }

        progressBar.setIndeterminate(true);

        new Thread(() -> {
            try {
                // Analizar el archivo para extraer las preguntas
                List<String> preguntas = FileSelector.analizarArchivo(file);

                if (preguntas.isEmpty()) {
                    outputArea.append("No se encontraron preguntas válidas en el archivo.\n");
                } else {
                    // Exportar las preguntas a un archivo Excel en el escritorio del usuario
                    String rutaCompletaExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";
                    ExcelCreator.exportExcel(preguntas, rutaCompletaExcel);
                    outputArea.append("Archivo Excel creado en: " + rutaCompletaExcel + "\n");
                }
            } catch (Exception e) {
                outputArea.append("Error al procesar el archivo: " + e.getMessage() + "\n");
            } finally {
                progressBar.setIndeterminate(false);
            }
        }).start();
    }

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
