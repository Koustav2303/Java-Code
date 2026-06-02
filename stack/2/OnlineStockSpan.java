import java.util.Stack;

/**
 * PROBLEM: Online Stock Span
 * * Design an algorithm that collects daily price quotes for some stock and returns the span 
 * of that stock's price for the current day.
 * The span of the stock's price in one day is the maximum number of consecutive days (starting 
 * from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * * Strategy:
 * Store elements in a monotonic decreasing stack as pairs: `[price, cumulative_span]`.
 * When a new price arrives, continuously pop smaller or equal elements from the stack and 
 * aggregate their spans into the current day's span.
 * * Complexity:
 * Time Complexity: Amortized O(1) per `next()` invocation.
 * Space Complexity: O(N) worst-case storage footprint.
 */
public class OnlineStockSpan {
    private Stack<int[]> stack; // Each element is an array: [price, span]

    public OnlineStockSpan() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        
        // Accumulate spans of all previous days with a smaller or equal price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        OnlineStockSpan stockSpanner = new OnlineStockSpan();
        System.out.print(stockSpanner.next(100) + " "); // 1
        System.out.print(stockSpanner.next(80) + " ");  // 1
        System.out.print(stockSpanner.next(60) + " ");  // 1
        System.out.print(stockSpanner.next(70) + " ");  // 2
        System.out.print(stockSpanner.next(60) + " ");  // 1
        System.out.print(stockSpanner.next(75) + " ");  // 4
        System.out.println(stockSpanner.next(85));      // 6
        // Expected Output sequence: 1 1 1 2 1 4 6
    }
}