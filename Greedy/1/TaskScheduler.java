import java.util.Arrays;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        
        Arrays.sort(frequencies);
        int maxFreq = frequencies[25];
        int maxCount = 0;
        
        // Count how many tasks tie for the maximum frequency
        for (int i = 25; i >= 0; i--) {
            if (frequencies[i] == maxFreq) maxCount++;
            else break;
        }
        
        // Calculate the intervals based strictly on the most frequent tasks and the cooldown chunks
        int requiredLength = (maxFreq - 1) * (n + 1) + maxCount;
        
        // The answer is either the formula, or the raw number of tasks (if no idle time is needed)
        return Math.max(requiredLength, tasks.length);
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2; // Cooldown
        System.out.println("Minimum intervals required: " + leastInterval(tasks, n));
    }
}