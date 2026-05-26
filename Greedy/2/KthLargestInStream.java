import java.util.PriorityQueue;

public class KthLargestInStream {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.poll(); // Discard everything except the top K elements
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] initialStream = {4, 5, 8, 2};
        KthLargestInStream stream = new KthLargestInStream(3, initialStream);
        
        System.out.println("Adding 3, 3rd largest is: " + stream.add(3)); // returns 4
        System.out.println("Adding 5, 3rd largest is: " + stream.add(5)); // returns 5
        System.out.println("Adding 10, 3rd largest is: " + stream.add(10)); // returns 5
    }
}