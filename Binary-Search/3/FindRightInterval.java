import java.util.Arrays;
import java.util.HashMap;

public class FindRightInterval {
    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] result = new int[n];
        int[] starts = new int[n];
        HashMap<Integer, Integer> startToIndex = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            startToIndex.put(starts[i], i);
        }
        
        Arrays.sort(starts);
        
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            int low = 0, high = n - 1;
            int rightStart = -1;
            
            // Binary search to find the smallest start time >= end
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (starts[mid] >= end) {
                    rightStart = starts[mid];
                    high = mid - 1; // Try to find a smaller valid start
                } else {
                    low = mid + 1;
                }
            }
            
            result[i] = (rightStart == -1) ? -1 : startToIndex.get(rightStart);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = {{3,4}, {2,3}, {1,2}};
        System.out.println("Right intervals: " + Arrays.toString(findRightInterval(intervals))); // [-1, 0, 1]
    }
}