package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int minNumber;
    private final int[] numbers;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMinNumber() {
        return this.minNumber;
    }

    @Override
    public void run() {
        minNumber = Arrays.stream(numbers).min().getAsInt();
    }
}
// END
