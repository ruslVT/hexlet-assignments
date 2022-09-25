package exercise;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class SorterTest {

    @Test
    void testTakeOldestMans() {

        List<Map<String, String>> users1 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> actual1 = Sorter.takeOldestMans(users1);
        List<String> expected1 = List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev");
        assertThat(actual1).isEqualTo(expected1);

        List<Map<String, String>> users2 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Evgeny Aleksandrov", "birthday", "1999-03-10", "gender", "male")
        );

        List<String> actual2 = Sorter.takeOldestMans(users2);
        List<String> expected2 = List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev", "Evgeny Aleksandrov");
        assertThat(actual2).isEqualTo(expected2);

        List<Map<String, String>> users3 = List.of(
                Map.of("name", "Olga Nikolaevna", "birthday", "1990-12-27", "gender", "female"),
                Map.of("name", "Marina Petrovna", "birthday", "1989-11-23", "gender", "female"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );

        List<String> actual3 = Sorter.takeOldestMans(users3);
        List<String> expected3 = List.of("John Smith");
        assertThat(actual3).isEqualTo(expected3);
    }

}
// END


