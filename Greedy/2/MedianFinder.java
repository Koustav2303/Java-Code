import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> smallHalf; // Max-Heap
    private PriorityQueue<Integer> largeHalf; // Min-Heap

    public MedianFinder() {
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        largeHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Add to the Max-Heap first
        smallHalf.add(num);
        
        // Ensure every number in smallHalf is <= every number in largeHalf
        largeHalf.add(smallHalf.poll());
        
        // Balance the sizes so smallHalf is always equal to or 1 element larger than largeHalf
        if (smallHalf.size() < largeHalf.size()) {
            smallHalf.add(largeHalf.poll());
        }
    }
    
    public double findMedian() {
        if (smallHalf.size() > largeHalf.size()) {
            return smallHalf.peek(); // Odd total elements
        }
        // Even total elements: average the roots of both heaps
        return (smallHalf.peek() + largeHalf.peek()) / 2.0; 
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median so far: " + mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println("Median so far: " + mf.findMedian()); // 2.0
    }
}