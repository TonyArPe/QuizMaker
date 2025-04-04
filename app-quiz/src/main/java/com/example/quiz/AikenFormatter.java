package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase util para convertir preguntas tipo test a formato Aiken.
 * Se espera que cada pregunta esté separada por saltos de línea,
 * y la respuesta sea una letra (A, B, C, D...) en la última línea.
 * @author TonyArPe
 * @version 1.4
 * @since 04/04/2025
 */
public class AikenFormatter {
    /**
     * Convierte una lista de preguntas tipo test al formato Aiken.
     * 
     * @param questions Lista de preguntas tipo test.
     * @return Lista de preguntas en formato Aiken.
     */
    public static List<String> aikenConverter(List<String> preguntas){
        List<String> aikenConvertidas = new ArrayList<>();

        for(String pregunta : preguntas){
            String[] lineas = pregunta.split("\\r?\\n");
            StringBuilder aiken = new StringBuilder();

            String respuesta = null;

            for(String linea: lineas){
                String lineaSinEspacios = linea.trim();

                // Si es solo una letra A-D asumimos que es la respuesta
                if(lineaSinEspacios.matches("^[A-D]$")){
                    respuesta = lineaSinEspacios;
                } else {
                    // Si no es una letra, asumimos que es una pregunta o una opción
                    aiken.append(lineaSinEspacios).append("\n");
                }
            }

            if(respuesta != null){
                aiken.append("ANSWER: ").append(respuesta);
                aikenConvertidas.add(aiken.toString());
            }else{
                System.out.println("Error: No se encontró respuesta en la pregunta: " + pregunta);
            }
        }
        return aikenConvertidas;
    }
}
