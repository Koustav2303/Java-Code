public class FrequencyCounter {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 4};
        int target = 2, count = 0;
        for (int i : nums) if (i == target) count++;
        System.out.println("Frequency of " + target + ": " + count);
    }
}