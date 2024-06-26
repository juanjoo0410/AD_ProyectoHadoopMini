package framework.model;

/**
 * La clase Pair representa un par clave-valor genérico. Es útil para almacenar
 * y manejar datos que vienen en pares.
 *
 * @author Grupo # 6
 */
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
