package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> users) {

        return users.stream()
                .filter(map -> map.containsValue("male"))
                .sorted(Comparator.comparingLong(map -> LocalDate.parse(map.get("birthday"))
                        .toEpochDay()))
                .flatMap(map -> Stream.of(map.get("name")))
                .collect(Collectors.toList());
    }
}
// END
