package framework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class Output {
    private String outputFile;
    private List<Map.Entry<String, Integer>> outputBuffer;

    public Output(String outputFile) {
        this.outputFile = outputFile;
    }

    public void setOutputBuffer(List<Map.Entry<String, Integer>> outputBuffer) {
        this.outputBuffer = outputBuffer;
    }

    public void escribirArchivo() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (Map.Entry<String, Integer> entry : outputBuffer) {
            writer.write(entry.getKey() + "\t" + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }
}
