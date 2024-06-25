package framework.model;

/**
 *
 * @author JuanJoo
 */
public class Partitioner<K2, V2> {
    private int numReduceNodes;

    public Partitioner(int numReduceNodes) {
        this.numReduceNodes = numReduceNodes;
    }

    public int getPartition(K2 key) {
        return Math.abs(key.hashCode()) % numReduceNodes;
    }
}
