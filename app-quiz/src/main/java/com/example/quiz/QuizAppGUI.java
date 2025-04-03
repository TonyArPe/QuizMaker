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
 * @version 1.3
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

        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel instructionLabel = new JLabel("Seleccione el archivo de preguntas (.docx):");
        fileNameField = new JTextField(30);
        JButton selectFileButton = new JButton("Seleccionar Archivo");
        topPanel.add(instructionLabel);
        topPanel.add(fileNameField);
        topPanel.add(selectFileButton);

        // Área de salida
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Añadir todo al panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.SOUTH);

        add(mainPanel);

        // Acción del botón
        selectFileButton.addActionListener(e -> selectAndProcessFile());
    }

    /**
     * Diálogo para seleccionar archivo y procesarlo.
     */
    private void selectAndProcessFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileNameField.setText(selectedFile.getAbsolutePath());
            outputArea.setText(""); // Limpiar área anterior
            outputArea.append("Archivo seleccionado: " + selectedFile.getAbsolutePath() + "\n");

            // Validar que sea .docx
            if (!selectedFile.getName().toLowerCase().endsWith(".docx")) {
                outputArea.append("Error: El archivo seleccionado no es un documento .docx\n");
                return;
            }

            processSelectedFile(selectedFile);
        }
    }

    /**
     * Procesa el archivo: analiza y exporta las preguntas a Excel.
     */
    private void processSelectedFile(File file) {
        if (!file.exists() || !file.canRead()) {
            outputArea.append("Error: No se puede leer el archivo seleccionado.\n");
            return;
        }

        progressBar.setIndeterminate(true);

        new Thread(() -> {
            try {
                List<String> preguntas = FileSelector.analizarArchivo(file);

                if (preguntas.isEmpty()) {
                    outputArea.append("No se encontraron preguntas válidas en el archivo.\n");
                } else {
                    String rutaCompletaExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";
                    ExcelCreator.exportExcel(preguntas, rutaCompletaExcel);
                    outputArea.append("Archivo Excel creado en: " + rutaCompletaExcel + "\n");

                    // Mostrar mensaje de éxito
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "¡Exportación completada!\nArchivo guardado en el escritorio.", "Éxito", JOptionPane.INFORMATION_MESSAGE)
                    );
                }
            } catch (Exception e) {
                outputArea.append("Error al procesar el archivo: " + e.getMessage() + "\n");
            } finally {
                SwingUtilities.invokeLater(() -> {
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(100);
                });
            }
        }).start();
    }

    /**
     * Método principal para iniciar la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
