package framework.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la lectura de datos de entrada desde un archivo.
 *
 * @author Grupo # 6
 */
public class InputComponent {

    private String inputFile;

    public InputComponent(String inputFile) {
        this.inputFile = inputFile;
    }

    //Lee el contenido del archivo de entrada y lo convierte en una lista de pares clave-valor.
    public List<Pair<String, String>> readInput() {
        List<Pair<String, String>> input = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(inputFile);
        if (inputStream == null) {
            System.err.println("Archivo no encontrado: " + inputFile);
            return input;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                // Usamos el número de línea como clave y la línea completa como valor
                input.add(new Pair<>(String.valueOf(lineNumber), line));
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
