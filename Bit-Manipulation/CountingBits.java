import java.util.Arrays;

public class CountingBits {
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // The number of 1s in 'i' is the number of 1s in 'i / 2' 
            // plus 1 if 'i' is odd.
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Counting bits from 0 to " + n + ":");
        System.out.println(Arrays.toString(countBits(n)));
        // Expected: [0, 1, 1, 2, 1, 2]
    }
}