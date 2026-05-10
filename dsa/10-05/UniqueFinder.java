public class UniqueFinder {
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        int res = 0;
        for (int n : nums) res ^= n;
        System.out.println("Unique element: " + res);
    }
}