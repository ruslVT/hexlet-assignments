package exercise;

import java.util.List;
import java.util.Set;

// BEGIN
public class App {
    private static final Set<String> FREE_EMAILS = Set.of("yandex.ru", "gmail.com", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails
                .stream()
                .map(email -> email.split("@")[1])
                .filter(FREE_EMAILS::contains)
                .count();
    }
}
// END
