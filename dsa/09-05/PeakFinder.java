public class PeakFinder {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        for (int i = 0; i < nums.length; i++) {
            boolean left = (i == 0) || (nums[i] > nums[i-1]);
            boolean right = (i == nums.length - 1) || (nums[i] > nums[i+1]);
            if (left && right) {
                System.out.println("Peak at index: " + i);
                break;
            }
        }
    }
}