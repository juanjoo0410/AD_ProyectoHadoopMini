package framework.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que maneja la escritura de los resultados finales del proceso MapReduce
 * a un archivo.
 *
 * @author Grupo # 6
 */
public class OutputComponent<K, V> {

    private String outputFile;

    public OutputComponent(String outputFile) {
        this.outputFile = outputFile;
    }

    //Escribe los pares clave-valor resultantes en el archivo de salida.
    public void writeOutput(List<Pair<K, V>> output) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Pair<K, V> pair : output) {
                bw.write(pair.getKey() + " " + pair.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
