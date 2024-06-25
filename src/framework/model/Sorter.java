package framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JuanJoo
 */
public class Sorter<K2, V2> {
    public List<Pair<K2, List<V2>>> sort(List<Pair<K2, V2>> input) {
        Map<K2, List<V2>> grouped = new HashMap<>();
        for (Pair<K2, V2> pair : input) {
            grouped.computeIfAbsent(pair.getKey(), k -> new ArrayList<>()).add(pair.getValue());
        }

        List<Pair<K2, List<V2>>> sorted = new ArrayList<>();
        for (Map.Entry<K2, List<V2>> entry : grouped.entrySet()) {
            sorted.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        return sorted;
    }
}
