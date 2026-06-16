import java.util.PriorityQueue;

/**
 * PROBLEM: Kth Largest Element in an Array
 * * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * * Strategy: Bounded Value Min-Heap
 * Maintain a Min-Heap of size `k`. Push numbers into the heap sequentially. 
 * Once the heap size exceeds `k`, pop the smallest element. After processing the entire array, 
 * the top element of the heap is guaranteed to be the K-th largest value.
 * * Complexity:
 * Time Complexity: O(N log K)
 * Space Complexity: O(K)
 */
public class KthLargestElementInAnArray {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Discard smaller values outside the top-K window
            }
        }
        return minHeap.peek(); // Top element is the Kth largest
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println("2nd largest array element value: " + findKthLargest(nums, 2)); // 5
    }
}