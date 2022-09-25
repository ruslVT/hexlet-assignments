package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static Map<String, Integer> getWordCount(String string) {
        Map<String, Integer> resultMap = new HashMap<>();

        if (string.isEmpty()) {
            return resultMap;
        } else {
            String[] arrayString = string.split(" ");
            for (String str : arrayString) {
                resultMap.put(str, resultMap.getOrDefault(str, 0) + 1);
            }
        }

        return resultMap;
    }

    public static String toString(Map<String, Integer> stringMap) {
        StringBuilder resultString;

        if (stringMap.isEmpty()) {
            return "{}";
        } else {
            resultString = new StringBuilder("{\n");
            for (Map.Entry<String, Integer> map : stringMap.entrySet()) {
                resultString.append("  ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
            }
            resultString.append("}");
        }

        return resultString.toString();
    }
}
//END
