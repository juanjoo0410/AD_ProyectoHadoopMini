package tasks;

import framework.model.Pair;
import framework.model.Tarea;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tareas a ejecutar La función de mapeo filtra y emite solo los datos
 * relevantes, mientras que la función de reducción realiza una agregación
 * simple pero efectiva.
 *
 * @author Grupo # 6
 */
public class MapReduceTasks {
//Tarea 1: Contar el número de respuestas HTTP 404.

    public static void executeTask1(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            if (value.contains(" 404 ")) {
                return Collections.singletonList(new Pair<>("404", 1));
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            int sum = 0;
            for (Integer value : values) {
                sum += value;
            }
            return sum;
        });
        tarea.run();
    }

    //Tarea 2: Contar el número de solicitudes de archivos GIF.
    public static void executeTask2(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            if (value.contains(".gif")) {
                return Collections.singletonList(new Pair<>("gif", 1));
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            int sum = 0;
            for (Integer value : values) {
                sum += value;
            }
            return sum;
        });
        tarea.run();
    }

    //Tarea 3: Contar el número de solicitudes por hora.
    public static void executeTask3(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            String[] parts = value.split(" ");
            if (parts.length >= 2) {
                String hour = parts[1].substring(1, 3);
                return Collections.singletonList(new Pair<>(hour, 1));
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            int sum = 0;
            for (Integer value : values) {
                sum += value;
            }
            return sum;
        });
        tarea.run();
    }

    //Tarea 4: Encontrar registros donde la temperatura superficial y la sensación térmica son diferentes.
    public static void executeTask4(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            String[] parts = value.split(",");
            if (parts.length >= 13) {
                String surfaceTemp = parts[8];
                String windChill = parts[12];
                if (!surfaceTemp.equals(windChill)) {
                    return Collections.singletonList(new Pair<>("different", value));
                }
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            StringBuilder sb = new StringBuilder();
            for (String value : values) {
                sb.append(value).append("\n");
            }
            return sb.toString();
        });
        tarea.run();
    }

    //Tarea 5: Encontrar registros con lluvia y mostrar la cantidad de lluvia, humedad y sensación térmica.
    public static void executeTask5(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            String[] parts = value.split(",");
            if (parts.length >= 13) {
                double rainfall = Double.parseDouble(parts[5]);
                if (rainfall > 0) {
                    String humidity = parts[9];
                    String windChill = parts[12];
                    String result = String.format("(%.1f, %s, %s)", rainfall, humidity, windChill);
                    return Collections.singletonList(new Pair<>("rainfall", result));
                }
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            StringBuilder sb = new StringBuilder();
            for (String value : values) {
                sb.append(value).append("\n");
            }
            return sb.toString();
        });
        tarea.run();
    }

    //Tarea 6: Encontrar la temperatura mínima y máxima.
    public static void executeTask6(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, Double, String> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            String[] parts = value.split(",");
            if (parts.length >= 9) {
                try {
                    double temp = Double.parseDouble(parts[8]);
                    return Arrays.asList(
                            new Pair<>("min", temp),
                            new Pair<>("max", temp)
                    );
                } catch (NumberFormatException e) {
                    // Ignorar líneas con formato incorrecto
                }
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {

            if (key.equals("min")) {
                Double min = Double.MAX_VALUE;
                for (Double value : values) {
                    if (value < min) {
                        min = value;
                    }
                }
                return String.valueOf(min);
            } else {
                Double max = Double.MIN_VALUE;
                for (Double value : values) {
                    if (value > max) {
                        max = value;
                    }
                }
                return String.valueOf(max);
            }
        });
        tarea.run();
    }

    //Tarea 7: Encontrar estados "tristes" (promedio de felicidad < 2) con ranking de Twitter.
    public static void executeTask7(String inputFile, String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(inputFile);
        tarea.setOutputFile(outputFile);
        tarea.setNodes(nodes);
        tarea.setMapFunction((key, value) -> {
            String[] parts = value.split("\t");
            if (parts.length >= 7) {
                try {
                    double happinessAvg = Double.parseDouble(parts[2]);
                    String twitterRank = parts[4];
                    if (happinessAvg < 2 && !twitterRank.equals("--")) {
                        return Collections.singletonList(new Pair<>("sad", parts[0]));
                    }
                } catch (NumberFormatException e) {
                    // Ignorar líneas con formato incorrecto
                }
            }
            return Collections.emptyList();
        });
        tarea.setReduceFunction((key, values) -> {
            return String.join(",", values);
        });
        tarea.run();
    }
}
