import java.util.HashSet;
import java.util.PriorityQueue;

public class UglyNumberII {
    public static int nthUglyNumber(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        HashSet<Long> seen = new HashSet<>(); // To avoid duplicates
        
        long[] primeFactors = {2, 3, 5};
        minHeap.add(1L);
        seen.add(1L);
        
        long currentUgly = 1;
        
        for (int i = 0; i < n; i++) {
            currentUgly = minHeap.poll();
            
            for (long factor : primeFactors) {
                long nextUgly = currentUgly * factor;
                if (!seen.contains(nextUgly)) {
                    seen.add(nextUgly);
                    minHeap.add(nextUgly);
                }
            }
        }
        return (int) currentUgly;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("The " + n + "th ugly number is: " + nthUglyNumber(n)); 
        // 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
    }
}