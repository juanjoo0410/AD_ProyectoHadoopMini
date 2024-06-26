package framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que implementa la funcionalidad de ordenamiento y agrupación en el
 * proceso MapReduce.
 *
 * @author Grupo # 6
 */
public class Sorter<K2, V2> {

    //Ordena y agrupa los pares clave-valor intermedios por clave.
    public List<Pair<K2, List<V2>>> sort(List<Pair<K2, V2>> input) {
        // Agrupa los valores por clave
        Map<K2, List<V2>> grouped = new HashMap<>();
        for (Pair<K2, V2> pair : input) {
            grouped.computeIfAbsent(pair.getKey(), k -> new ArrayList<>()).add(pair.getValue());
        }

        // Convierte el mapa agrupado en una lista de pares clave-lista de valores
        List<Pair<K2, List<V2>>> sorted = new ArrayList<>();
        for (Map.Entry<K2, List<V2>> entry : grouped.entrySet()) {
            sorted.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        return sorted;
    }
}
