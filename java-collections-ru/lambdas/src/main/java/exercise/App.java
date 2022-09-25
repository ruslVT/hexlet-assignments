package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] arrayImage) {

        return Arrays.stream(arrayImage)
                .map(App::increaseRow)
                .flatMap(row -> Stream.of(row, row))  // double horizontal row
                .toArray(String[][]::new);

    }

    public static String[] increaseRow(String[] row) {
        return Stream.of(row)
                .flatMap(item -> Stream.of(item, item))
                .toArray(String[]::new);
    }

}
// END
