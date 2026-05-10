public class RootFinder {
    public static void main(String[] args) {
        int x = 25;
        long low = 1, high = x, ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid <= x) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        System.out.println("Floor square root: " + ans);
    }
}