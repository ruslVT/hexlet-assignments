package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> homeList, int elementsCount) {

        return homeList.stream()
                .sorted(Home::compareTo)
                .limit(elementsCount)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
