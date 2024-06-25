package tasks;

import framework.model.Pair;
import framework.model.Tarea;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author JuanJoo
 */
public class MapReduceTasks {

    private static final String DIRFILE1 = "src/weblog.txt";
    private static final String DIRFILE2 = "src/JCMB_last31days.csv";
    private static final String DIRFILE3 = "src/happiness.txt";

    public static void executeTask1(String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE1);
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

    public static void executeTask2(String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE1);
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

    public static void executeTask3(String outputFile, int nodes) {
        Tarea<String, String, String, Integer, Integer> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE1);
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

    public static void executeTask4(String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE2);
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

    public static void executeTask5(String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE2);
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

    public static void executeTask6(String outputFile, int nodes) {
        Tarea<String, String, String, Double, String> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE2);
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

    public static void executeTask7(String outputFile, int nodes) {
        Tarea<String, String, String, String, String> tarea = new Tarea<>();
        tarea.setInputFile(DIRFILE3);
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
            return String.join(", ", values);
        });
        tarea.run();
    }
}
