package framework;

import java.util.ArrayList;
import java.util.function.*;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class MapNode {
    private Function<Map.Entry<String, String>, List<Map.Entry<String, Integer>>> mapFunction;
    private List<Map.Entry<String, String>> inputBuffer;
    private List<Map.Entry<String, Integer>> outputBuffer;

    public MapNode(Function<Map.Entry<String, String>, List<Map.Entry<String, Integer>>> mapFunction) {
        this.mapFunction = mapFunction;
        this.outputBuffer = new ArrayList<>();
    }

    public void setInputBuffer(List<Map.Entry<String, String>> inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public List<Map.Entry<String, Integer>> ejecutar() {
        for (Map.Entry<String, String> pair : inputBuffer) {
            outputBuffer.addAll(mapFunction.apply(pair));
        }
        return outputBuffer;
    }
}
