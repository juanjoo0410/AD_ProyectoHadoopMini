package framework.model;

import framework.interfaces.MapFunction;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un nodo de mapeo en el proceso MapReduce.
 *
 * @author Grupo # 6
 */
public class MapNode<K1, V1, K2, V2> {

    private MapFunction<K1, V1, K2, V2> mapFunction;
    private List<Pair<K2, V2>> outputBuffer;

    public MapNode(MapFunction<K1, V1, K2, V2> mapFunction) {
        this.mapFunction = mapFunction;
        this.outputBuffer = new ArrayList<>();
    }

    //Aplica la función de mapeo a un par clave-valor de entrada y almacena el resultado.
    public void map(K1 key, V1 value) {
        List<Pair<K2, V2>> result = mapFunction.map(key, value);
        outputBuffer.addAll(result);
    }

    //Retorna los resultados acumulados de todas las operaciones de mapeo realizadas.
    public List<Pair<K2, V2>> getOutput() {
        return outputBuffer;
    }
}
