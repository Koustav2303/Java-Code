public class KthSmallestMultiplicationTable {
    public static int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countLessOrEqual(m, n, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    private static int countLessOrEqual(int m, int n, int target) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            // The number of elements <= target in row i is min(target / i, n)
            count += Math.min(target / i, n);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("5th smallest in 3x3 table: " + findKthNumber(3, 3, 5)); // 3
    }
}