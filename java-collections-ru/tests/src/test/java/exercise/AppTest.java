package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        assertThat(App.take(numbers, 3)).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 3)));
        assertThat(App.take(numbers, 8)).isEqualTo(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        assertThat(App.take(numbers, 0)).isEmpty();
        // END
    }
}
