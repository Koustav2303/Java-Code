import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public static String largestNumber(int[] nums) {
        // Convert integers to strings
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // Custom comparator to decide which combination is larger
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1); // Reverse order for descending
            }
        });

        // If after sorting the largest number is "0", the whole number is 0
        if (strNums[0].equals("0")) return "0";

        // Build the final string
        StringBuilder largest = new StringBuilder();
        for (String s : strNums) largest.append(s);

        return largest.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Largest possible number: " + largestNumber(nums));
    }
}