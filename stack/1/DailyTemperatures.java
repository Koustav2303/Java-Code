import java.util.Arrays;
import java.util.Stack;

/**
 * PROBLEM: Daily Temperatures
 * * Given an array of integers temperatures represents the daily temperatures, return an array answer 
 * such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. 
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * * Strategy: Monotonic Decreasing Stack
 * We traverse the array. We maintain a stack of *indices* whose temperatures are strictly decreasing.
 * When we encounter a temperature warmer than the index at the top of the stack, we have found the next 
 * warmer day for that index! We pop it, calculate the day gap, and repeat until the stack conditions match.
 * * Complexity:
 * Time Complexity: O(N) since every element is pushed and popped at most once.
 * Space Complexity: O(N) for the stack tracking.
 */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>(); // Stores array indices
        
        for (int i = 0; i < n; i++) {
            // While current temperature is warmer than the temperature of the index at the top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex; // Distance in days
            }
            stack.push(i);
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Days to wait for warmer temp: " + Arrays.toString(dailyTemperatures(temps)));
        // [1, 1, 4, 2, 1, 1, 0, 0]
    }
}