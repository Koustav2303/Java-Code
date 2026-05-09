public class MissingFinder {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 6}; // 3 is missing
        int n = 6;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int i : nums) actualSum += i;
        System.out.println("Missing Number: " + (expectedSum - actualSum));
    }
}