package framework.interfaces;

import framework.model.Pair;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public interface MapFunction<K1, V1, K2, V2> {
    public List<Pair<K2, V2>> map(K1 key, V1 value);
}
