package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class ReversedSequenceTest {


    private final CharSequence sequence = new ReversedSequence("privet");

    @Test
    void testReversedSequence() {

        String actual = sequence.toString();
        String expected = "tevirp";
        assertThat(actual).isEqualTo(expected);

        char actual2 = sequence.charAt(3);
        char expected2 = 'i';
        assertThat(actual2).isEqualTo(expected2);

        int actual3 = sequence.length();
        int expected3 = 6;
        assertThat(actual3).isEqualTo(expected3);

        String actual4 = (String) sequence.subSequence(1, 3);
        String expected4 = "ev";
        assertThat(actual4).isEqualTo(expected4);

    }

}
