import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * PROBLEM: K-th Smallest Prime Fraction
 * * You are given a sorted integer array arr containing 1 and prime numbers, and an integer k.
 * Return the kth smallest fraction arr[i] / arr[j].
 * * Approach:
 * Use a Min-Heap. Similar to "Merge K Sorted Lists". We start by pushing fractions formed by 
 * dividing every element by the largest element (arr[n-1]). 
 * Then, we pop the smallest fraction, and push the fraction formed by the same numerator but the 
 * NEXT largest denominator (arr[n-2]).
 */
public class KthSmallestPrimeFraction {
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        // Heap stores int[]: {numerator_index, denominator_index}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            // To avoid floating point precision issues, use cross multiplication:
            // a/b < c/d => a*d < c*b
            return Integer.compare(arr[a[0]] * arr[b[1]], arr[b[0]] * arr[a[1]]);
        });
        
        // Start by dividing everything by the largest possible denominator (arr[n-1])
        for (int i = 0; i < n - 1; i++) {
            minHeap.add(new int[]{i, n - 1});
        }
        
        while (k > 1) {
            int[] curr = minHeap.poll();
            int numIdx = curr[0];
            int denIdx = curr[1];
            
            // Move to the next smaller denominator for this numerator
            if (denIdx - 1 > numIdx) {
                minHeap.add(new int[]{numIdx, denIdx - 1});
            }
            k--;
        }
        
        int[] result = minHeap.poll();
        return new int[]{arr[result[0]], arr[result[1]]};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5};
        int k = 3;
        System.out.println("Kth smallest fraction: " + Arrays.toString(kthSmallestPrimeFraction(arr, k))); // [2, 5]
    }
}