package framework.interfaces;

/**
 *
 * @author JuanJoo
 */
public interface ReduceFunction<K2, V2, V3> {
    public V3 reduce(K2 key, Iterable<V2> values);
}
