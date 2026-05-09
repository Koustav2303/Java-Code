public class MinFinder {
    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 1, 9};
        int min = nums[0];
        for(int i : nums) if(i < min) min = i;
        System.out.println("Minimum: " + min);
    }
}