public class MinDaysBouquets {
    public static int minDays(int[] bloomDay, int m, int k) {
        // If we need more flowers than we have, it's impossible
        if ((long) m * k > bloomDay.length) return -1;
        
        int low = Integer.MAX_VALUE, high = 0;
        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canMakeBouquets(bloomDay, m, k, mid)) {
                high = mid; // Try fewer days
            } else {
                low = mid + 1; // We need more days
            }
        }
        return low;
    }

    private static boolean canMakeBouquets(int[] bloomDay, int m, int k, int days) {
        int bouquets = 0, adjacent = 0;
        for (int bloom : bloomDay) {
            if (bloom <= days) {
                adjacent++;
                if (adjacent == k) {
                    bouquets++;
                    adjacent = 0; // Reset for the next bouquet
                }
            } else {
                adjacent = 0; // Sequence broken
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        int[] bloomDay = {1, 10, 3, 10, 2};
        System.out.println("Minimum days to make 3 bouquets of 1 flower: " + minDays(bloomDay, 3, 1)); // 3
    }
}