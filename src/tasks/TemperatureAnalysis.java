package tasks;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class TemperatureAnalysis {
    public List<Map.Entry<String, Double>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, Double>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split(",");
        
        if (parts.length >= 9) {
            try {
                double surfaceTemp = Double.parseDouble(parts[8]);
                result.add(new SimpleEntry<>("temperature", surfaceTemp));
            } catch (NumberFormatException e) {
                // Ignorar líneas con formato incorrecto
            }
        }
        
        return result;
    }

    public Map.Entry<String, Double[]> reduceFunction(String key, List<Double> values) {
        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;

        for (double temp : values) {
            if (temp < minTemp) {
                minTemp = temp;
            }
            if (temp > maxTemp) {
                maxTemp = temp;
            }
        }

        return new SimpleEntry<>(key, new Double[]{minTemp, maxTemp});
    }
}
