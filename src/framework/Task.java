package framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.*;

/**
 *
 * @author Grupo # 6
 */
public class Task {
    private String inputFile;
    private String outputFile;
    private int numNodes;
    private Function<Map.Entry<String, String>, List<Map.Entry<String, Integer>>> mapFunction;
    private BiFunction<String, List<Integer>, Map.Entry<String, Integer>> reduceFunction;
    private BiFunction<String, List<Integer>, Map.Entry<String, Integer>> combinerFunction;
    
    public void run() throws IOException {
        // Entrada
        Input entrada = new Input(inputFile);
        List<Map.Entry<String, String>> inputBuffer = entrada.leerArchivo();

        // Nodos Map
        List<Map.Entry<String, Integer>> mapOutputBuffer = new ArrayList<>();
        int chunkSize = (int) Math.ceil((double) inputBuffer.size() / numNodes);
        for (int i = 0; i < numNodes; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, inputBuffer.size());
            List<Map.Entry<String, String>> chunk = inputBuffer.subList(start, end);
            MapNode nodoMap = new MapNode(mapFunction);
            nodoMap.setInputBuffer(chunk);
            mapOutputBuffer.addAll(nodoMap.ejecutar());
        }

        // Combinador (opcional)
        if (combinerFunction != null) {
            Map<String, List<Integer>> combinedBuffer = new SortAndShuffle().ordenar(mapOutputBuffer);
            mapOutputBuffer.clear();
            for (Map.Entry<String, List<Integer>> entry : combinedBuffer.entrySet()) {
                mapOutputBuffer.add(combinerFunction.apply(entry.getKey(), entry.getValue()));
            }
        }

        // Particionador
        Particioner particionador = new Particioner();
        Map<Integer, List<Map.Entry<String, Integer>>> partitions = particionador.partition(mapOutputBuffer, numNodes);

        // Nodos Reduce
        List<Map.Entry<String, Integer>> reduceOutputBuffer = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            List<Map.Entry<String, Integer>> partition = partitions.get(i);
            Map<String, List<Integer>> groupedBuffer = new SortAndShuffle().ordenar(partition);
            ReduceNode nodoReduce = new ReduceNode(reduceFunction);
            nodoReduce.setInputBuffer(groupedBuffer);
            reduceOutputBuffer.addAll(nodoReduce.ejecutar());
        }

        // Salida
        Output salida = new Output(outputFile);
        salida.setOutputBuffer(reduceOutputBuffer);
        salida.escribirArchivo();

        // Mostrar resultado
        for (Map.Entry<String, Integer> entry : reduceOutputBuffer) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public void setNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setMapFunction(Function<Map.Entry<String, String>, List<Map.Entry<String, Integer>>> mapFunction) {
        this.mapFunction = mapFunction;
    }

    public void setReduceFunction(BiFunction<String, List<Integer>, Map.Entry<String, Integer>> reduceFunction) {
        this.reduceFunction = reduceFunction;
    }

    public void setCombinerFunction(BiFunction<String, List<Integer>, Map.Entry<String, Integer>> combinerFunction) {
        this.combinerFunction = combinerFunction;
    }
}
