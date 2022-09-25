package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static boolean scrabble(String chars, String string) {
        List<Character> characters = new ArrayList<>();

        for (char ch : chars.toLowerCase().toCharArray()) {
            characters.add(ch);
        }

        for (int i = 0; i < string.length(); i++) {
            if (characters.contains(string.toLowerCase().charAt(i))) {
                characters.remove((Character) string.toLowerCase().charAt(i));
            } else {
                return false;
            }
        }

        return true;
    }
}
//END
