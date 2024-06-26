package framework.model;

/**
 * Clase que implementa la lógica de particionamiento en el proceso MapReduce.
 *
 * @author Grupo # 6
 */
public class Partitioner<K2, V2> {

    private int numReduceNodes;

    public Partitioner(int numReduceNodes) {
        this.numReduceNodes = numReduceNodes;
    }

    //Determina a qué nodo de reducción debe enviarse una clave específica.
    public int getPartition(K2 key) {
        return Math.abs(key.hashCode()) % numReduceNodes;
    }
}
