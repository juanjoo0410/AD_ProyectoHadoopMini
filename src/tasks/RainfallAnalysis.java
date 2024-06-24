package tasks;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class RainfallAnalysis {
    public List<Map.Entry<String, String>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split(",");
        
        if (parts.length >= 13) {
            try {
                double rainfall = Double.parseDouble(parts[5]);
                if (rainfall > 0) {
                    double relativeHumidity = Double.parseDouble(parts[9]);
                    double windChill = Double.parseDouble(parts[12]);
                    String value = String.format("%.2f,%.2f,%.2f", rainfall, relativeHumidity, windChill);
                    result.add(new SimpleEntry<>(line, value));
                }
            } catch (NumberFormatException e) {
                // Ignorar líneas con formato incorrecto
            }
        }
        
        return result;
    }

    public Map.Entry<String, String> reduceFunction(String key, List<String> values) {
        // El reduce simplemente devuelve el valor original, ya que no se necesita ningún procesamiento adicional.
        return new SimpleEntry<>(key, values.get(0)); // Tomamos el primer valor, ya que todos serán iguales
    }
}
