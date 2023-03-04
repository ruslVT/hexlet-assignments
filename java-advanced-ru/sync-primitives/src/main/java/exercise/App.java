package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();
        Thread safetyList1 = new Thread(new ListThread(list));
        Thread safetyList2 = new Thread(new ListThread(list));

        safetyList1.start();
        safetyList2.start();

        try {
            safetyList1.join();
            safetyList2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.getSize());
        // END
    }
}

