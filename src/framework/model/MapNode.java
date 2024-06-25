/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package framework.model;

import framework.interfaces.MapFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanJoo
 */
public class MapNode<K1, V1, K2, V2> {
    private MapFunction<K1, V1, K2, V2> mapFunction;
    private List<Pair<K2, V2>> outputBuffer;

    public MapNode(MapFunction<K1, V1, K2, V2> mapFunction) {
        this.mapFunction = mapFunction;
        this.outputBuffer = new ArrayList<>();
    }

    public void map(K1 key, V1 value) {
        List<Pair<K2, V2>> result = mapFunction.map(key, value);
        outputBuffer.addAll(result);
    }

    public List<Pair<K2, V2>> getOutput() {
        return outputBuffer;
    }
}
