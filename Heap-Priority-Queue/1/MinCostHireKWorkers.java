/**
 * PROBLEM: Minimum Cost to Hire K Workers
 * * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality 
 * of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
 * We want to hire exactly k workers to form a paid group. Every worker must be paid in proportion 
 * to their quality compared to other workers, and at least their minimum wage.
 * Return the least amount of money needed to form a paid group.
 * * Example:
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * * Approach:
 * The cost is determined by the maximum (wage / quality) ratio in the group * sum(quality).
 * Sort workers by their ratio. Then use a Max-Heap to track the smallest K qualities seen so far, 
 * maintaining a running sum to compute the cost at each step.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinCostHireKWorkers {
    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] workers = new double[n][2];
        
        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i]; // Ratio
            workers[i][1] = (double) quality[i];           // Quality
        }
        
        // Sort ascending by ratio
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        
        // Max-Heap to track the highest qualities and evict them to minimize the sum
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        double minCost = Double.MAX_VALUE;
        double qualitySum = 0;
        
        for (double[] worker : workers) {
            double ratio = worker[0];
            double qual = worker[1];
            
            maxHeap.add(qual);
            qualitySum += qual;
            
            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll(); // Remove the largest quality
            }
            
            if (maxHeap.size() == k) {
                // Cost = Maximum ratio in the group * sum of their qualities
                minCost = Math.min(minCost, qualitySum * ratio);
            }
        }
        
        return minCost;
    }

    public static void main(String[] args) {
        int[] quality = {10, 20, 5};
        int[] wage = {70, 50, 30};
        int k = 2;
        System.out.println("Min cost: " + mincostToHireWorkers(quality, wage, k)); // 105.0
    }
}