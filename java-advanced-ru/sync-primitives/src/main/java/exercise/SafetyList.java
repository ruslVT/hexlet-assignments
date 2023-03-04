package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[1];
    private int size = 0;

    public SafetyList() {
    }

    public synchronized void add(int num) {
        if (array.length == size) {
            int[] tempArray = array;
            array = new int[size * 2];
            System.arraycopy(tempArray, 0, array, 0, tempArray.length);
        }

        array[size++] = num;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return size;
    }

    // END
}
