package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Clase que proporciona una interfaz gráfica para seleccionar un archivo de
 * preguntas,
 * analizarlo y exportar las preguntas a un archivo Excel y/o TXT (formato
 * Aiken).
 * 
 * @author TonyArPe
 * @version 1.4
 * @since 02/04/2025
 */
public class QuizAppGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField fileNameField;
    private JTextArea outputArea;
    private JProgressBar progressBar;
    private JButton exportToExcelButton; // Botón para Excel
    private JButton exportToTxtButton;   // Botón para Aiken
    private List<String> preguntasExtraidas;

    public QuizAppGUI() {
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeComponents();
        setVisible(true);
        System.out.println(">> Versión 1.3.3 - Probando cambios recientes");
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Panel superior general con GridLayout 2x1
        JPanel topContainer = new JPanel(new GridLayout(2, 1, 5, 5));

        // Panel 1: selección de archivo
        JPanel topPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel instructionLabel = new JLabel("Seleccione el archivo de preguntas (.docx):");
        fileNameField = new JTextField(30);
        JButton selectFileButton = new JButton("Seleccionar Archivo");
        topPanel1.add(instructionLabel);
        topPanel1.add(fileNameField);
        topPanel1.add(selectFileButton);

        // Panel 2: botones de acción
        JPanel topPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exportToExcelButton = new JButton("Exportar a Excel");
        exportToTxtButton = new JButton("Exportar a TXT (Aiken)");
        JButton salirButton = new JButton("Salir");

        exportToExcelButton.setEnabled(false);
        exportToTxtButton.setEnabled(false);

        topPanel2.add(exportToExcelButton);
        topPanel2.add(exportToTxtButton);
        topPanel2.add(salirButton);

        // Añadir ambos al contenedor
        topContainer.add(topPanel1);
        topContainer.add(topPanel2);

        // Área de salida
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Agregar todo al main panel
        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.SOUTH);
        add(mainPanel);

        // Acción seleccionar archivo
        selectFileButton.addActionListener(e -> selectAndProcessFile());

        // Acción exportar a Excel
        exportToExcelButton.addActionListener(ev -> {
            if (preguntasExtraidas == null || preguntasExtraidas.isEmpty()) {
                outputArea.append("No hay preguntas para exportar.\n");
                return;
            }
            String rutaExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";
            ExcelCreator.exportExcel(preguntasExtraidas, rutaExcel);
            outputArea.append("Archivo Excel creado en: " + rutaExcel + "\n");
        });

        // Acción exportar a Aiken
        exportToTxtButton.addActionListener(ev -> {
            if (preguntasExtraidas == null || preguntasExtraidas.isEmpty()) {
                outputArea.append("No hay preguntas para exportar.\n");
                return;
            }
            String rutaTxt = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "PreguntasAiken.txt";
            TxtCreator.exportToAikenFormat(preguntasExtraidas, rutaTxt);
            outputArea.append("Preguntas exportadas en formato Aiken a: " + rutaTxt + "\n");
        });

        // Acción salir
        salirButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void selectAndProcessFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Validar extensión antes de setText
            if (!selectedFile.getName().toLowerCase().endsWith(".docx")) {
                outputArea.append("Error: El archivo seleccionado no es un documento .docx\n");
                return;
            }

            fileNameField.setText(selectedFile.getAbsolutePath());
            outputArea.setText("");
            outputArea.append("Archivo seleccionado: " + selectedFile.getAbsolutePath() + "\n");

            processSelectedFile(selectedFile);
        }
    }

    private void processSelectedFile(File file) {
        if (!file.exists() || !file.canRead()) {
            outputArea.append("Error: No se puede leer el archivo seleccionado.\n");
            return;
        }

        progressBar.setIndeterminate(true);

        new Thread(() -> {
            try {
                List<String> preguntas = FileSelector.analizarArchivo(file);
                preguntasExtraidas = preguntas;

                if (preguntas.isEmpty()) {
                    outputArea.append("No se encontraron preguntas válidas en el archivo.\n");
                } else {
                    // Activar botones
                    SwingUtilities.invokeLater(() -> {
                        exportToTxtButton.setEnabled(true);
                        exportToExcelButton.setEnabled(true);
                    });

                    // Mensaje de éxito
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                            "¡Archivo procesado correctamente! Ahora puedes exportar a Excel o TXT (Aiken).",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizAppGUI::new);
    }
}
