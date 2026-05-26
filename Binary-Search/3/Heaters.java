import java.util.Arrays;

public class Heaters {
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int maxRadius = 0;
        
        for (int house : houses) {
            // Binary search for the closest heater
            int closestHeaterDist = findClosestHeaterDist(house, heaters);
            maxRadius = Math.max(maxRadius, closestHeaterDist);
        }
        return maxRadius;
    }
    
    private static int findClosestHeaterDist(int house, int[] heaters) {
        int low = 0, high = heaters.length - 1;
        int closestDist = Integer.MAX_VALUE;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int heater = heaters[mid];
            
            closestDist = Math.min(closestDist, Math.abs(heater - house));
            
            if (heater == house) {
                return 0; // Perfect match
            } else if (heater < house) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return closestDist;
    }

    public static void main(String[] args) {
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        System.out.println("Minimum heater radius: " + findRadius(houses, heaters)); // 1
    }
}