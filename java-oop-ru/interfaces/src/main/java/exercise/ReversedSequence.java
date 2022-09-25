package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    String str;

    public ReversedSequence(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();
        this.str = stringBuilder.toString();
    }

    @Override
    public boolean isEmpty() {
        return str.length() == 0;
    }

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int i) {
        return str.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return str.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return str;
    }
}
// END
