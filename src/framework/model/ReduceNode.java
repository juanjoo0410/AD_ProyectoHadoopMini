package framework.model;

import framework.interfaces.ReduceFunction;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un nodo de reducción en el proceso MapReduce.
 *
 * @author Grupo # 6
 */
public class ReduceNode<K2, V2, V3> {

    private ReduceFunction<K2, V2, V3> reduceFunction;
    private List<Pair<K2, V3>> outputBuffer;

    public ReduceNode(ReduceFunction<K2, V2, V3> reduceFunction) {
        this.reduceFunction = reduceFunction;
        this.outputBuffer = new ArrayList<>();
    }

    //Aplica la función de reducción a una clave y sus valores asociados.
    public void reduce(K2 key, Iterable<V2> values) {
        V3 result = reduceFunction.reduce(key, values);
        outputBuffer.add(new Pair<>(key, result));
    }

    //Retorna los resultados acumulados de todas las operaciones de reducción realizadas.
    public List<Pair<K2, V3>> getOutput() {
        return outputBuffer;
    }
}
