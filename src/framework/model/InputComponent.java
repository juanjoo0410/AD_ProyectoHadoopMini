package framework.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
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
