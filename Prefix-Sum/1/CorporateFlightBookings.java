import java.util.Arrays;

/**
 * PROBLEM: Corporate Flight Bookings (Difference Array / Line Sweep)
 * * There are n flights numbered from 1 to n.
 * You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] 
 * represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved.
 * Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
 * * Example:
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 * * Approach:
 * Instead of adding seats to every flight in the range O(N^2), we use a "Difference Array".
 * Add `seats` at the `first` flight index.
 * Subtract `seats` at the `last + 1` flight index (meaning the seats are no longer occupied).
 * Finally, run a 1D Prefix Sum across the array to compute the final seat counts in O(N).
 */
public class CorporateFlightBookings {
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        
        // Step 1: Create the Difference Array markings
        for (int[] booking : bookings) {
            int first = booking[0] - 1; // Convert to 0-based index
            int last = booking[1] - 1;
            int seats = booking[2];
            
            result[first] += seats;
            if (last + 1 < n) {
                result[last + 1] -= seats;
            }
        }
        
        // Step 2: Compute the Prefix Sum to apply the changes
        for (int i = 1; i < n; i++) {
            result[i] += result[i - 1];
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        System.out.println("Seats per flight: " + Arrays.toString(corpFlightBookings(bookings, n))); 
        // [10, 55, 45, 25, 25]
    }
}