import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PROBLEM: Task Scheduler
 * * Given a characters array tasks, representing the tasks a CPU needs to do, and an integer n,
 * representing the cooling period between two same tasks.
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 * * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B
 * * Approach:
 * Use a Max-Heap to always process the most frequent task first.
 * Use a Queue to track tasks that are in cooldown, storing their remaining frequency and the time they unlock.
 */
public class TaskSchedulerHeap {
    public static int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for (char t : tasks) counts.put(t, counts.getOrDefault(t, 0) + 1);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(counts.values());
        
        // Queue stores int[] : [remaining_frequency, time_it_becomes_available]
        Queue<int[]> cooldownQueue = new LinkedList<>();
        int time = 0;
        
        while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
            time++;
            
            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1;
                if (count > 0) {
                    cooldownQueue.add(new int[]{count, time + n});
                }
            }
            
            // If the front of the cooldown queue is ready to be processed, put it back in the heap
            if (!cooldownQueue.isEmpty() && cooldownQueue.peek()[1] == time) {
                maxHeap.add(cooldownQueue.poll()[0]);
            }
        }
        
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println("Least time: " + leastInterval(tasks, n)); // 8
    }
}