package framework.model;

import framework.interfaces.ReduceFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public class ReduceNode<K2, V2, V3> {
    private ReduceFunction<K2, V2, V3> reduceFunction;
    private List<Pair<K2, V3>> outputBuffer;

    public ReduceNode(ReduceFunction<K2, V2, V3> reduceFunction) {
        this.reduceFunction = reduceFunction;
        this.outputBuffer = new ArrayList<>();
    }

    public void reduce(K2 key, Iterable<V2> values) {
        V3 result = reduceFunction.reduce(key, values);
        outputBuffer.add(new Pair<>(key, result));
    }

    List<Pair<K2, V3>> getOutput() {
        return outputBuffer;
    }
}
