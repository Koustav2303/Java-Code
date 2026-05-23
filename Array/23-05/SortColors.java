import java.util.Arrays;

public class SortColors {
    public static void sort(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] colors = {2, 0, 2, 1, 1, 0};
        System.out.println("Original: " + Arrays.toString(colors));
        sort(colors);
        System.out.println("Sorted:   " + Arrays.toString(colors));
    }
}