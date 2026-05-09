public class MaxFinder {
    public static void main(String[] args) {
        int[] nums = {12, 45, 2, 89, 33};
        int max = nums[0];
        for(int i : nums) if(i > max) max = i;
        System.out.println("Maximum: " + max);
    }
}