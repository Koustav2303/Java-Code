import java.util.Arrays;

public class ZeroMover {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[pos];
                nums[pos++] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}