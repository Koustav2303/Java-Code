import java.util.Stack;

public class OnlineStockSpan {
    // Stack stores integer arrays: [price, span]
    private Stack<int[]> stack;

    public OnlineStockSpan() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        // Pop all previous days that are less than or equal to current price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]; // Accumulate their spans
        }
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        OnlineStockSpan stockSpanner = new OnlineStockSpan();
        System.out.println("Span for 100: " + stockSpanner.next(100)); // 1
        System.out.println("Span for 80: " + stockSpanner.next(80));   // 1
        System.out.println("Span for 60: " + stockSpanner.next(60));   // 1
        System.out.println("Span for 70: " + stockSpanner.next(70));   // 2 (70, 60)
        System.out.println("Span for 60: " + stockSpanner.next(60));   // 1
        System.out.println("Span for 85: " + stockSpanner.next(85));   // 6 (85, 60, 70, 60, 80)
    }
}