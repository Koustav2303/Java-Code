import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        // By default, PriorityQueue is a Min-Heap in Java
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);
            // If the heap grows larger than k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // The root of the Min-Heap is the Kth largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println(k + "nd largest element: " + findKthLargest(nums, k));
    }
}