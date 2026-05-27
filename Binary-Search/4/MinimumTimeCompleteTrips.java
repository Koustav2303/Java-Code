public class MinimumTimeCompleteTrips {
    public static long minimumTime(int[] time, int totalTrips) {
        long low = 1;
        long maxTime = 0;
        for (int t : time) maxTime = Math.max(maxTime, t);
        
        // Worst case: the fastest bus does all the trips
        long high = maxTime * totalTrips;
        
        while (low < high) {
            long mid = low + (high - low) / 2;
            
            if (canCompleteTrips(time, totalTrips, mid)) {
                high = mid; // Try to finish in less time
            } else {
                low = mid + 1; // Not enough time
            }
        }
        return low;
    }
    
    private static boolean canCompleteTrips(int[] times, int totalTrips, long givenTime) {
        long tripsCompleted = 0;
        for (int time : times) {
            tripsCompleted += (givenTime / time);
        }
        return tripsCompleted >= totalTrips;
    }

    public static void main(String[] args) {
        int[] time = {1, 2, 3};
        int totalTrips = 5;
        System.out.println("Minimum time to complete trips: " + minimumTime(time, totalTrips)); // 3
    }
}