package framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class Particioner {

    public Map<Integer, List<Map.Entry<String, Integer>>> partition(List<Map.Entry<String, Integer>> inputBuffer, int numReducers) {
        Map<Integer, List<Map.Entry<String, Integer>>> partitions = new HashMap<>();
        for (int i = 0; i < numReducers; i++) {
            partitions.put(i, new ArrayList<>());
        }
        for (Map.Entry<String, Integer> pair : inputBuffer) {
            int partition = (pair.getKey().hashCode() & Integer.MAX_VALUE) % numReducers;
            partitions.get(partition).add(pair);
        }
        return partitions;
    }
}
