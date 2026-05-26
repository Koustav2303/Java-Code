import java.util.Arrays;

public class AggressiveCows {
    public static int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int low = 1; // Minimum possible distance
        int high = position[position.length - 1] - position[0]; // Maximum possible distance
        int best = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canPlaceCows(position, m, mid)) {
                best = mid;    // This distance works, record it
                low = mid + 1; // Try for a larger distance
            } else {
                high = mid - 1; // Distance too large, try smaller
            }
        }
        return best;
    }
    
    private static boolean canPlaceCows(int[] position, int m, int minDistance) {
        int cowsPlaced = 1;
        int lastPlacedPosition = position[0];
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPlacedPosition >= minDistance) {
                cowsPlaced++;
                lastPlacedPosition = position[i];
                if (cowsPlaced == m) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stalls = {1, 2, 8, 4, 9};
        int cows = 3;
        System.out.println("Maximized minimum distance: " + maxDistance(stalls, cows)); // 3
    }
}