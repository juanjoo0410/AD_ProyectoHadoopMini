package tasks;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class WebLogHourAnalysis {

    public static List<Map.Entry<String, Integer>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split(" ");

        if (parts.length >= 2) {
            String date = parts[1];
            String hour = date.substring(1, 3); // Extraer la hora del formato [DD:HH:MM:SS]
            result.add(new AbstractMap.SimpleEntry<>(hour, 1));
        }

        return result;
    }

    public static Map.Entry<String, Integer> reduceFunction(String key, List<Integer> values) {
        int sum = values.stream().mapToInt(Integer::intValue).sum();
        return new AbstractMap.SimpleEntry<>(key, sum);
    }
}
