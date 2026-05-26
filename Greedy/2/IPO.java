import java.util.PriorityQueue;

public class IPO {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Min-Heap sorting projects by capital required: [capital, profit]
        PriorityQueue<int[]> minCapitalHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // Max-Heap sorting projects by profit: [profit]
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < profits.length; i++) {
            minCapitalHeap.add(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < k; i++) {
            // Unlock all projects we can currently afford
            while (!minCapitalHeap.isEmpty() && minCapitalHeap.peek()[0] <= w) {
                maxProfitHeap.add(minCapitalHeap.poll()[1]);
            }

            // If we can't afford any more projects, we are done
            if (maxProfitHeap.isEmpty()) break;

            // Greedily complete the most profitable unlocked project
            w += maxProfitHeap.poll();
        }

        return w;
    }

    public static void main(String[] args) {
        int k = 2, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};
        System.out.println("Maximized Capital: " + findMaximizedCapital(k, w, profits, capital));
    }
}