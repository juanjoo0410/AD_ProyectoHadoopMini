package framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class SortAndShuffle {
    public Map<String, List<Integer>> ordenar(List<Map.Entry<String, Integer>> inputBuffer) {
        Map<String, List<Integer>> grouped = new HashMap<>();
        for (Map.Entry<String, Integer> pair : inputBuffer) {
            if (!grouped.containsKey(pair.getKey())) {
                grouped.put(pair.getKey(), new ArrayList<>());
            }
            grouped.get(pair.getKey()).add(pair.getValue());
        }
        return grouped;
    }
}
