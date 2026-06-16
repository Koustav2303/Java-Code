import java.util.Collections;
import java.util.PriorityQueue;

/**
 * PROBLEM: Find Median from Data Stream
 * * Design a data structure that supports adding integers from a continuous data stream 
 * and retrieving the median value in constant time O(1).
 * * Strategy: Min-Max Heap Split Balancing
 * Divide incoming numbers into two halves:
 * 1. A Max-Heap (`lowerHalfHeap`) to store the smaller half of the numbers.
 * 2. A Min-Heap (`upperHalfHeap`) to store the larger half of the numbers.
 * Maintain size balance: the lower half can contain at most one more element than the upper half. 
 * If size balance breaks, balance elements via pop-pushes.
 * * Complexity:
 * Time Complexity: Add: O(log N), Find Median: O(1).
 */
public class FindMedianFromDataStream {
    private PriorityQueue<Integer> lowerHalfHeap; // Max-heap containing left half elements
    private PriorityQueue<Integer> upperHalfHeap; // Min-heap containing right half elements

    public FindMedianFromDataStream() {
        lowerHalfHeap = new PriorityQueue<>(Collections.reverseOrder());
        upperHalfHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Enqueue item into lower half and route through upper half to enforce value boundaries
        lowerHalfHeap.add(num);
        upperHalfHeap.add(lowerHalfHeap.poll());
        
        // Re-balance size properties to keep lowerHalfHeap dominant
        if (lowerHalfHeap.size() < upperHalfHeap.size()) {
            lowerHalfHeap.add(upperHalfHeap.poll());
        }
    }
    
    public double findMedian() {
        if (lowerHalfHeap.size() > upperHalfHeap.size()) {
            return lowerHalfHeap.peek();
        }
        return (lowerHalfHeap.peek() + upperHalfHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Current running median: " + medianFinder.findMedian()); // 1.5
        medianFinder.addNum(3);
        System.out.println("Median after adding element: " + medianFinder.findMedian()); // 2.0
    }
}