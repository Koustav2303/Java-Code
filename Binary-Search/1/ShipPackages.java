public class ShipPackages {
    public static int shipWithinDays(int[] weights, int days) {
        int low = 0; // The ship must carry at least the heaviest single item
        int high = 0; // The ship could theoretically carry all items in 1 day
        
        for (int weight : weights) {
            low = Math.max(low, weight);
            high += weight;
        }

        while (low <= high) {
            int midCapacity = low + (high - low) / 2;
            
            if (canShip(weights, midCapacity, days)) {
                high = midCapacity - 1; // Try a smaller capacity
            } else {
                low = midCapacity + 1;  // Capacity too small, increase it
            }
        }
        return low;
    }

    private static boolean canShip(int[] weights, int capacity, int days) {
        int daysNeeded = 1;
        int currentWeight = 0;
        
        for (int weight : weights) {
            if (currentWeight + weight > capacity) {
                daysNeeded++; // Ship it next day
                currentWeight = 0;
            }
            currentWeight += weight;
        }
        return daysNeeded <= days;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println("Minimum ship capacity: " + shipWithinDays(weights, days)); // 15
    }
}