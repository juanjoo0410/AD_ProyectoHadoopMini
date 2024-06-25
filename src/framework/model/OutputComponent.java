package framework.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public class OutputComponent<K, V> {
    private String outputFile;

    public OutputComponent(String outputFile) {
        this.outputFile = outputFile;
    }

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
