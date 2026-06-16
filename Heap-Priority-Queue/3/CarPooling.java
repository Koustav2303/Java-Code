import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * PROBLEM: Car Pooling
 * * There is a car with a fixed capacity. Given an array of trips where trips[i] = [numPassengers, from, to],
 * return true if it is possible to pick up and drop off all passengers for all given trips without exceeding capacity.
 * * Strategy: Drop-off Scheduling Min-Heap
 * Sort trips by their pickup location. Maintain a Min-Heap tracking active trips sorted by their drop-off point. 
 * Before picking up passengers for a new trip, continuously evict completed trips from the heap whose 
 * drop-off locations are <= the current pickup point, restoring the car's capacity on the fly.
 * * Complexity:
 * Time Complexity: O(N log N) due to sorting.
 * Space Complexity: O(N) to store active trip coordinates in the heap.
 */
public class CarPooling {
    public static boolean carPooling(int[][] trips, int capacity) {
        // Sort trips primarily by their starting pickup point
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Min-heap tracking active trips sorted by their destination drop-off point: [to, passengers]
        PriorityQueue<int[]> activeTripsHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int currentPassengersValue = 0;
        
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int pickupLocation = trip[1];
            int dropoffLocation = trip[2];
            
            // Evict all completed trips whose drop-off location is behind the current pickup point
            while (!activeTripsHeap.isEmpty() && activeTripsHeap.peek()[0] <= pickupLocation) {
                currentPassengersValue -= activeTripsHeap.poll()[1];
            }
            
            // Allocate space for the incoming trip passengers
            currentPassengersValue += numPassengers;
            if (currentPassengersValue > capacity) {
                return false; // Out of vehicle seating capacity boundaries
            }
            
            activeTripsHeap.add(new int[]{dropoffLocation, numPassengers});
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        System.out.println("Can handle pool itinerary? " + carPooling(trips, 4)); // false (capacity exceeded at location 3)
    }
}