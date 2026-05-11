class Data { int val = 10; }

public class CopyDemo {
    public static void main(String[] args) {
        Data d1 = new Data();
        Data d2 = d1; // Shallow copy (same reference)
        d2.val = 20;
        System.out.println("d1 val (Shallow): " + d1.val); // Changes d1 too
    }
}