package framework.interfaces;

/**
 *
 * @author JuanJoo
 */
public interface CombinerFunction<K2, V2> {
    public V2 combine(K2 key, Iterable<V2> values);
}
