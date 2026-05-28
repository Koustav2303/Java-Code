import java.util.PriorityQueue;

/**
 * PROBLEM: Kth Largest Element in an Array
 * * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity. (Using a Heap gives O(N log K) which is standard).
 * * Example:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * * Approach:
 * Maintain a Min-Heap of size K. As we iterate through the array, we add elements to the heap.
 * If the heap size exceeds K, we remove the smallest element (the root).
 * By the end, the heap will contain exactly the K largest elements, and the root will be the Kth largest.
 */
public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        // PriorityQueue in Java is a Min-Heap by default
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.add(num);
            
            // If the heap grows larger than k, poll (remove) the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // The root of the heap is the Kth largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(k + "nd largest element: " + findKthLargest(nums, k)); // 5
    }
}