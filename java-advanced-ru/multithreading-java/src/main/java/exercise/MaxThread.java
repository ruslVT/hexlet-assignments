package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread{

    private int maxNumber;
    private final int[] numbers;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMaxNumber() {
        return this.maxNumber;
    }

    @Override
    public void run() {
        maxNumber = Arrays.stream(numbers).max().getAsInt();
    }
}
// END
