public class ArrayReverser {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        reverse(arr);
        for (int i : arr) System.out.print(i + " ");
    }

    public static void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}