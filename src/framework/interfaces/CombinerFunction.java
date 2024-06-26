package framework.interfaces;

/**
 * Interfaz que define la función combinadora, una optimización en algunos
 * sistemas MapReduce. Actúa como un "mini-reduce" local antes de la fase de
 * reducción final.
 *
 * @author Grupo # 6
 */
public interface CombinerFunction<K2, V2> {

    //Combina los valores asociados a una clave de forma local.
    //Ayuda a reducir la cantidad de datos transferidos entre nodos en un sistema distribuido.
    public V2 combine(K2 key, Iterable<V2> values);
}
