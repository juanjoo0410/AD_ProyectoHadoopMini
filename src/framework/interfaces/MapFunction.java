package framework.interfaces;

import framework.model.Pair;
import java.util.List;

/**
 * Interfaz que define la función de mapeo en el paradigma MapReduce.
 *
 * @author Grupo # 6
 */
public interface MapFunction<K1, V1, K2, V2> {

    //Transforma un par clave-valor de entrada en una lista de pares clave-valor 
    public List<Pair<K2, V2>> map(K1 key, V1 value);
}
