public class BitCounter {
    public static void main(String[] args) {
        int n = 11; // Binary: 1011
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        System.out.println("Number of 1s: " + count);
    }
}