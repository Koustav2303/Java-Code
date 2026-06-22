import java.util.Arrays;

/**
 * PROBLEM: Forward Looking MSD Radix Sort
 * * Optimize MSD string radix sorting by early-terminating uniform suffix blocks.
 * * Strategy: Suffix Homogeneity Scanning
 * Before initiating a recursive counting sort pass on a sub-bucket, scan ahead to check if all strings 
 * share the exact same character at the current index position. If they do, short-circuit the counting sort pass 
 * and advance directly to the next character position, eliminating redundant array allocations.
 */
public class ForwardLookingMSDRadixSort {
    private static final int R = 256;

    public static void sort(String[] arr) {
        String[] aux = new String[arr.length];
        sortMSD(arr, 0, arr.length - 1, 0, aux);
    }

    private static int charAt(String s, int d) {
        return (d < s.length()) ? s.charAt(d) : -1;
    }

    private static void sortMSD(String[] arr, int low, int high, int d, String[] aux) {
        if (high <= low) return;

        // Forward-looking scan: check if all strings share the same character at position d
        boolean uniform = true;
        int firstChar = charAt(arr[low], d);
        for (int i = low + 1; i <= high; i++) {
            if (charAt(arr[i], d) != firstChar) {
                uniform = false;
                break;
            }
        }

        // Short-circuit if character position is identical across the current sub-bucket
        if (uniform) {
            if (firstChar >= 0) sortMSD(arr, low, high, d + 1, aux);
            return;
        }

        int[] count = new int[R + 2];
        for (int i = low; i <= high; i++) count[charAt(arr[i], d) + 2]++;
        for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];
        for (int i = low; i <= high; i++) aux[count[charAt(arr[i], d) + 1]++] = arr[i];
        for (int i = low; i <= high; i++) arr[i] = aux[i - low];

        for (int r = 0; r < R; r++) {
            sortMSD(arr, low + count[r], low + count[r + 1] - 1, d + 1, aux);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"prefix_test1", "prefix_test2", "prefix_abc", "prefix_xyz"};
        sort(arr);
        System.out.println("Forward-Looking Short-Circuit Outcome:\n" + Arrays.toString(arr));
    }
}