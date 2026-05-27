public class CuttingRibbons {
    public static int maxLength(int[] ribbons, int k) {
        int low = 1, high = 0;
        for (int ribbon : ribbons) high = Math.max(high, ribbon);
        
        int best = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canCut(ribbons, k, mid)) {
                best = mid; // This length works, try cutting them longer
                low = mid + 1;
            } else {
                high = mid - 1; // Expected length too long, shorten it
            }
        }
        return best;
    }
    
    private static boolean canCut(int[] ribbons, int k, int length) {
        int count = 0;
        for (int ribbon : ribbons) {
            count += (ribbon / length);
        }
        return count >= k;
    }

    public static void main(String[] args) {
        int[] ribbons = {9, 7, 5};
        int k = 3;
        System.out.println("Max uniform ribbon length: " + maxLength(ribbons, k)); // 5
    }
}