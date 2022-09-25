package exercise;

import java.util.*;

// BEGIN
public class App {

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        LinkedHashMap<String, String> resultMap = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());

        for (String str : keySet) {
            if (!map1.containsKey(str) && map2.containsKey(str)) {
                resultMap.put(str, "added");
            } else if (map1.containsKey(str) && !map2.containsKey(str)) {
                resultMap.put(str, "deleted");
            } else if (!(map1.get(str).equals(map2.get(str)))) {
                resultMap.put(str, "changed");
            } else if (map1.get(str).equals(map2.get(str))) {
                resultMap.put(str, "unchanged");
            }
        }

        return resultMap;
    }
}
//END
