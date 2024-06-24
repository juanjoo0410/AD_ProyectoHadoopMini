package framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class Input {
    private String inputFile;
    private List<Map.Entry<String, String>> buffer;
    
    public Input(String inputFile) {
        this.inputFile = inputFile;
        this.buffer = new ArrayList<>();
    }

    public List<Map.Entry<String, String>> leerArchivo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.add(new AbstractMap.SimpleEntry<>(line, line));
        }
        reader.close();
        return buffer;
    }
}
