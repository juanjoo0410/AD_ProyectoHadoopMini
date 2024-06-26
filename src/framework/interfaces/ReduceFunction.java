package framework.interfaces;

/**
 * Interfaz que define la función de reducción en el paradigma MapReduce.
 *
 * @author Grupo # 6
 */
public interface ReduceFunction<K2, V2, V3> {

    //Combina o agrega los valores asociados a una clave para producir un resultado final.
    public V3 reduce(K2 key, Iterable<V2> values);
}
