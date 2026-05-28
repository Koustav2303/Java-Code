/**
 * PROBLEM: Car Pooling (Difference Array / Line Sweep)
 * * There is a car with capacity empty seats. The vehicle only drives east.
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] 
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off.
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * * Example:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * * Approach:
 * The maximum location value is 1000. We can create a timeline array of size 1001.
 * For each trip, add passengers at `from` and subtract at `to`.
 * Then, calculate the prefix sum across the timeline. If the sum ever exceeds `capacity`, return false.
 */
public class CarPooling {
    public static boolean carPooling(int[][] trips, int capacity) {
        int[] timeline = new int[1001];
        
        // Mark the events on the timeline
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            
            timeline[from] += passengers;
            timeline[to] -= passengers; // They get off, so we subtract
        }
        
        // Run a prefix sum to calculate the car's load at any given mile
        int currentLoad = 0;
        for (int loadChange : timeline) {
            currentLoad += loadChange;
            if (currentLoad > capacity) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;
        System.out.println("Can complete all trips? " + carPooling(trips, capacity)); // false
        
        int[][] trips2 = {{2, 1, 5}, {3, 5, 7}};
        System.out.println("Can complete all trips? " + carPooling(trips2, capacity)); // true
    }
}