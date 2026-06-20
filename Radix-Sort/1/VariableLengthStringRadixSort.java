import java.util.Arrays;

/**
 * PROBLEM: Variable Length String Radix Sort
 * * Alphabetize strings of different lengths using an LSD Radix Sort approach.
 * * Strategy: Maximal Padding Length Sweep
 * Find the maximum string length, `maxLength`. Loop backwards from `maxLength - 1` down to 0. 
 * For each index position, evaluate strings using an extended ASCII counting sort bucket size of 256. 
 * If a string is shorter than the current index position, treat its character value as 0 (a virtual padding character) 
 * to ensure shorter strings are correctly sorted before longer ones.
 */
public class VariableLengthStringRadixSort {
    public static void sortVariableStrings(String[] arr) {
        int n = arr.length;
        int maxLength = 0;
        for (String s : arr) {
            maxLength = Math.max(maxLength, s.length());
        }

        String[] output = new String[n];

        // LSD Loop: Scan backwards from the maximum string length position down to 0
        for (int d = maxLength - 1; d >= 0; d--) {
            int[] count = new int[257]; // Use 257 buckets to handle the 0 padding value offset safely

            for (int i = 0; i < n; i++) {
                int charCode = (d < arr[i].length()) ? (arr[i].charAt(d) + 1) : 0;
                count[charCode]++;
            }

            for (int i = 1; i < 257; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int charCode = (d < arr[i].length()) ? (arr[i].charAt(d) + 1) : 0;
                output[count[charCode] - 1] = arr[i];
                count[charCode]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"word", "a", "app", "apple", "zoo", "application", "bat"};
        sortVariableStrings(arr);
        System.out.println("Variable LSD Outcome: " + Arrays.toString(arr)); 
        // [a, app, apple, application, bat, word, zoo]
    }
}