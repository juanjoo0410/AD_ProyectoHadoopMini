package framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.*;

/**
 *
 * @author Grupo # 6
 */
public class ReduceNode {
    private BiFunction<String, List<Integer>, Map.Entry<String, Integer>> reduceFunction;
    private Map<String, List<Integer>> inputBuffer;
    private List<Map.Entry<String, Integer>> outputBuffer;
    
    public ReduceNode(BiFunction<String, List<Integer>, Map.Entry<String, Integer>> reduceFunction) {
        this.reduceFunction = reduceFunction;
        this.outputBuffer = new ArrayList<>();
    }

    public void setInputBuffer(Map<String, List<Integer>> inputBuffer) {
        this.inputBuffer = inputBuffer;
    }

    public List<Map.Entry<String, Integer>> ejecutar() {
        for (Map.Entry<String, List<Integer>> entry : inputBuffer.entrySet()) {
            outputBuffer.add(reduceFunction.apply(entry.getKey(), entry.getValue()));
        }
        return outputBuffer;
    }
}
