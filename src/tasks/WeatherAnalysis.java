package tasks;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class WeatherAnalysis {
    public static List<Map.Entry<String, String>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split(",");
        
        if (parts.length >= 14) {
            try {
                double surfaceTemp = Double.parseDouble(parts[8]);
                double windChill = Double.parseDouble(parts[12]);
                if (surfaceTemp != windChill) {
                    result.add(new SimpleEntry<>(line, line));
                }
            } catch (NumberFormatException e) {
                // Ignorar líneas con formato incorrecto
            }
        }
        
        return result;
    }

    public static Map.Entry<String, String> reduceFunction(String key, List<String> values) {
        // El reduce simplemente devuelve el valor original, ya que no se necesita ningún procesamiento adicional.
        return new SimpleEntry<>(key, key);
    }
}
