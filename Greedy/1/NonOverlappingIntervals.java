import java.util.Arrays;

public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Greedily sort by END times. Finishing earlier leaves more room for others.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;
        int currentEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            // If the next interval starts before the current one ends, it's an overlap
            if (intervals[i][0] < currentEnd) {
                count++; // We must remove it
            } else {
                // Otherwise, update the end boundary to this new interval
                currentEnd = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Intervals to remove: " + eraseOverlapIntervals(intervals)); // Output: 1
    }
}