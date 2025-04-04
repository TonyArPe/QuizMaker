package com.example.quiz;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Clase que proporciona una interfaz gráfica para seleccionar un archivo de
 * preguntas,
 * analizarlo y exportar las preguntas a un archivo Excel y/o TXT (formato Aiken).
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
    private JButton exportToTxtButton; // Ahora es un atributo de clase
    private List<String> preguntasExtraidas;
    

    public QuizAppGUI() {
        setTitle("Quiz App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Panel superior para selección de archivo
        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel instructionLabel = new JLabel("Seleccione el archivo de preguntas (.docx):");
        fileNameField = new JTextField(30);
        JButton selectFileButton = new JButton("Seleccionar Archivo");
        exportToTxtButton = new JButton("Exportar a TXT (Aiken)");
        exportToTxtButton.setEnabled(false);
        JButton salirButton = new JButton("Salir");


        // Añadirlos al panel
        topPanel.add(instructionLabel);
        topPanel.add(fileNameField);
        topPanel.add(selectFileButton);
        topPanel.add(exportToTxtButton);
        topPanel.add(salirButton);


        // Área de salida
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Añadir al panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(progressBar, BorderLayout.SOUTH);
        add(mainPanel);

        // Botón seleccionar archivo
        selectFileButton.addActionListener(e -> selectAndProcessFile());

        // Botón exportar a Aiken
        exportToTxtButton.addActionListener(ev -> {
            if (preguntasExtraidas == null || preguntasExtraidas.isEmpty()) {
                outputArea.append("No hay preguntas para exportar.\n");
                return;
            }

            String rutaTxt = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "PreguntasAiken.txt";
            TxtCreator.exportToAikenFormat(preguntasExtraidas, rutaTxt);
            outputArea.append("Preguntas exportadas en formato Aiken a: " + rutaTxt + "\n");
        });

        salirButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
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
                    // Exportar a Excel
                    String rutaExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Preguntas.xlsx";
                    ExcelCreator.exportExcel(preguntas, rutaExcel);
                    outputArea.append("Archivo Excel creado en: " + rutaExcel + "\n");

                    // Habilitar botón de exportación a Aiken
                    SwingUtilities.invokeLater(() -> exportToTxtButton.setEnabled(true));

                    // Mensaje de éxito
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this,
                            "¡Exportación completada!\nArchivo guardado en el escritorio.", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE));
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
