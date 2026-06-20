import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * PROBLEM: Queue Bucket Radix Sort
 * * Implement Radix Sort explicitly using an array of Queue collections instead of cumulative prefix sum tracking blocks.
 * * Strategy: FIFO Collection Bucket Routing
 * Instantiate an array of 10 Queue items to represent digit buckets 0 through 9. 
 * For each digit position, traverse the array and enqueue elements into their matching digit bucket. 
 * Rebuild the array by dequeueing elements from the buckets sequentially. This approach clearly demonstrates 
 * the stable FIFO grouping mechanism of Radix Sort without needing complex index calculation pointers.
 */
public class QueueBucketRadixSort {
    @SuppressWarnings("unchecked")
    public static void queueRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = arr[0];
        for (int val : arr) if (val > max) max = val;

        // Initialize 10 independent FIFO queue list containers
        Queue<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<>();
        }

        // Loop through each digit position
        for (int exp = 1; max / exp > 0; exp *= 10) {
            
            // Step 1: Route elements into their matching digit buckets
            for (int val : arr) {
                int digitBucketIndex = (val / exp) % 10;
                buckets[digitBucketIndex].add(val);
            }

            // Step 2: Dequeue elements sequentially to rebuild the array
            int arrayIndex = 0;
            for (int i = 0; i < 10; i++) {
                while (!buckets[i].isEmpty()) {
                    arr[arrayIndex++] = buckets[i].remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {902, 45, 7, 12, 89, 23, 1000, 44};
        queueRadixSort(arr);
        System.out.println("Queue Bucket Sorted: " + Arrays.toString(arr));
    }
}