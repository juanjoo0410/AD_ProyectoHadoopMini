package framework.model;

import framework.interfaces.CombinerFunction;
import framework.interfaces.MapFunction;
import framework.interfaces.ReduceFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public class Tarea<K1, V1, K2, V2, V3> {

    private String inputFile;
    private String outputFile;
    private int numNodes;
    private MapFunction<K1, V1, K2, V2> mapFunction;
    private ReduceFunction<K2, V2, V3> reduceFunction;
    private CombinerFunction<K2, V2> combinerFunction;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public void setNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setMapFunction(MapFunction<K1, V1, K2, V2> mapFunction) {
        this.mapFunction = mapFunction;
    }

    public void setReduceFunction(ReduceFunction<K2, V2, V3> reduceFunction) {
        this.reduceFunction = reduceFunction;
    }

    public void setCombinerFunction(CombinerFunction<K2, V2> combinerFunction) {
        this.combinerFunction = combinerFunction;
    }

    public void run() {
        // Implementar la lógica de ejecución de la tarea MapReduce
        InputComponent input = new InputComponent(inputFile);
        List<Pair<String, String>> inputData = input.readInput();

        // Fase Map
        List<MapNode<K1, V1, K2, V2>> mapNodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            mapNodes.add(new MapNode<>(mapFunction));
        }

        for (int i = 0; i < inputData.size(); i++) {
            Pair<String, String> pair = inputData.get(i);
            mapNodes.get(i % numNodes).map((K1) pair.getKey(), (V1) pair.getValue());
        }

        // Combinar (si se proporciona una función combinadora)
        List<Pair<K2, V2>> combinedOutput = new ArrayList<>();
        if (combinerFunction != null) {
            Combiner<K2, V2> combiner = new Combiner<>(combinerFunction);
            for (MapNode<K1, V1, K2, V2> mapNode : mapNodes) {
                combinedOutput.addAll(combiner.combine(mapNode.getOutput()));
            }
        } else {
            for (MapNode<K1, V1, K2, V2> mapNode : mapNodes) {
                combinedOutput.addAll(mapNode.getOutput());
            }
        }

        // Particionar y ordenar
        Partitioner<K2, V2> partitioner = new Partitioner<>(numNodes);
        Sorter<K2, V2> sorter = new Sorter<>();
        List<List<Pair<K2, V2>>> partitions = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            partitions.add(new ArrayList<>());
        }

        for (Pair<K2, V2> pair : combinedOutput) {
            int partition = partitioner.getPartition(pair.getKey());
            partitions.get(partition).add(pair);
        }

        // Fase Reduce
        List<ReduceNode<K2, V2, V3>> reduceNodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            reduceNodes.add(new ReduceNode<>(reduceFunction));
        }

        for (int i = 0; i < numNodes; i++) {
            List<Pair<K2, List<V2>>> sortedPartition = sorter.sort(partitions.get(i));
            for (Pair<K2, List<V2>> pair : sortedPartition) {
                reduceNodes.get(i).reduce(pair.getKey(), pair.getValue());
            }
        }

        // Recopilar resultados
        List<Pair<K2, V3>> finalOutput = new ArrayList<>();
        for (ReduceNode<K2, V2, V3> reduceNode : reduceNodes) {
            finalOutput.addAll(reduceNode.getOutput());
        }

        // Escribir resultados
        OutputComponent output = new OutputComponent(outputFile);
        output.writeOutput(finalOutput);

        // Mostrar resultados
        System.out.println("Resultados de la tarea MapReduce:");
        for (Pair<K2, V3> pair : finalOutput) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
    }
}
