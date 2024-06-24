package tasks;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class WebLogGifAnalysis {
    public static List<Map.Entry<String, Integer>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split(" ");
        
        if (parts.length >= 3) {
            String request = parts[2];
            if (request.contains(".gif")) {
                result.add(new AbstractMap.SimpleEntry<>("gif", 1));
            }
        }
        
        return result;
    }

    public static Map.Entry<String, Integer> reduceFunction(String key, List<Integer> values) {
        int sum = values.stream().mapToInt(Integer::intValue).sum();
        return new AbstractMap.SimpleEntry<>(key, sum);
    }
}
