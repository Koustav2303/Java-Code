import java.util.Collections;
import java.util.PriorityQueue;

/**
 * PROBLEM: Find Median from Data Stream
 * * The median is the middle value in an ordered integer list. If the size of the list is even, 
 * there is no middle value, and the median is the mean of the two middle values.
 * Implement the MedianFinder class to add numbers from a stream and find the median.
 * * Example:
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * * Approach:
 * Use Two Heaps. A Max-Heap stores the smaller half of the numbers. A Min-Heap stores the larger half.
 * We balance them such that their sizes differ by at most 1.
 */
public class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // Lower half
    private PriorityQueue<Integer> minHeap; // Upper half

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Add to maxHeap first
        maxHeap.add(num);
        
        // Balance: ensure every number in maxHeap is <= every number in minHeap
        minHeap.add(maxHeap.poll());
        
        // Ensure maxHeap always has equal or exactly 1 more element than minHeap
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek(); // Odd total elements
        }
        // Even total elements
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median: " + mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println("Median: " + mf.findMedian()); // 2.0
    }
}