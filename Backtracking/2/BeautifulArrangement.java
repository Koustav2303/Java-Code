public class BeautifulArrangement {
    static int count = 0;

    public static int countArrangement(int n) {
        count = 0;
        backtrack(n, 1, new boolean[n + 1]);
        return count;
    }

    private static void backtrack(int n, int pos, boolean[] used) {
        if (pos > n) {
            count++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i] && (i % pos == 0 || pos % i == 0)) {
                used[i] = true;
                backtrack(n, pos + 1, used);
                used[i] = false; // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println("Total beautiful arrangements for " + n + ": " + countArrangement(n));
        // Output: 3
    }
}