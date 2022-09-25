package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String getForwardedVariables(String content) {

        return Stream.of(content.split("\n"))
                .filter(str -> str.startsWith("environment"))
                .flatMap(str -> Arrays.stream(str.split(",")))
                .filter(str -> str.contains("X_FORWARDED_"))
                .map(str -> str.replaceAll("(.*)X_FORWARDED_", ""))
                .map(str -> str.replaceAll("\"", ""))
                .collect(Collectors.joining(","));
    }

}
//END
