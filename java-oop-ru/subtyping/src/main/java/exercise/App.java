package exercise;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage keyValueStorage) {
        Set<String> keySet = keyValueStorage.toMap().keySet();

        Map<String, String> tempMap = keyValueStorage.toMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        for (String key : keySet) {
            keyValueStorage.unset(key);
        }

        for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            keyValueStorage.set(entry.getKey(), entry.getValue());
        }
    }
}
// END
