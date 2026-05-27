import java.util.Arrays;

public class RussianDollEnvelopes {
    public static int maxEnvelopes(int[][] envelopes) {
        // Sort width ascending. If widths are equal, sort height DESCENDING.
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int[] tails = new int[envelopes.length];
        int size = 0;
        
        for (int[] env : envelopes) {
            int height = env[1];
            int low = 0, high = size;
            
            // Standard O(N log N) LIS binary search
            while (low != high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < height) low = mid + 1;
                else high = mid;
            }
            
            tails[low] = height;
            if (low == size) size++;
        }
        return size;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5,4}, {6,4}, {6,7}, {2,3}};
        System.out.println("Max Russian Dolls: " + maxEnvelopes(envelopes)); // 3 ([2,3] -> [5,4] -> [6,7])
    }
}