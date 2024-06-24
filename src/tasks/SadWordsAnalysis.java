package tasks;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Grupo # 6
 */
public class SadWordsAnalysis {
    public List<Map.Entry<String, String>> mapFunction(Map.Entry<String, String> entry) {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        String line = entry.getValue();
        String[] parts = line.split("\t");
        
        if (parts.length >= 7) {
            try {
                double happinessAverage = Double.parseDouble(parts[2]);
                String twitterRank = parts[4];
                
                if (happinessAverage < 2 && !twitterRank.equals("--")) {
                    result.add(new SimpleEntry<>(parts[0], parts[0]));
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
