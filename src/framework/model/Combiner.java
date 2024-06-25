package framework.model;

import framework.interfaces.CombinerFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JuanJoo
 */
public class Combiner<K2, V2> {
    private CombinerFunction<K2, V2> combinerFunction;

    public Combiner(CombinerFunction<K2, V2> combinerFunction) {
        this.combinerFunction = combinerFunction;
    }

    public List<Pair<K2, V2>> combine(List<Pair<K2, V2>> input) {
        Map<K2, List<V2>> grouped = new HashMap<>();
        for (Pair<K2, V2> pair : input) {
            grouped.computeIfAbsent(pair.getKey(), k -> new ArrayList<>()).add(pair.getValue());
        }

        List<Pair<K2, V2>> output = new ArrayList<>();
        for (Map.Entry<K2, List<V2>> entry : grouped.entrySet()) {
            V2 combinedValue = combinerFunction.combine(entry.getKey(), entry.getValue());
            output.add(new Pair<>(entry.getKey(), combinedValue));
        }
        return output;
    }
}
