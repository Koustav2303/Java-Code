import java.util.Arrays;

/**
 * PROBLEM: Ternary Radix Tree Sort
 * * Implement a memory-efficient variant of MSD Radix Sort that avoids allocating extra count arrays 
 * or copying data to auxiliary structures.
 * * Strategy: Three-Way Alphabetical Pivot Partitioning
 * This approach processes characters from left to right, combining the character-grouping mechanics 
 * of radix sort with the quicksort partitioning model. 
 * Pick a pivot character at the current index position. Divide the array into three sub-partitions: 
 * elements with a character less than, equal to, or greater than the pivot. 
 * Recursively sort the three partitions, advancing the character index position only for the "equal" bucket.
 */
public class TernaryRadixTreeSort {
    public static void sort(String[] arr) {
        if (arr == null || arr.length == 0) return;
        sort(arr, 0, arr.length - 1, 0);
    }

    private static int charAt(String s, int d) {
        return (d < s.length()) ? s.charAt(d) : -1;
    }

    private static void sort(String[] arr, int low, int high, int d) {
        if (high <= low) return;

        int lt = low;
        int gt = high;
        int pivotChar = charAt(arr[low], d);
        int i = low + 1;

        // Perform standard three-way quicksort partitioning
        while (i <= gt) {
            int t = charAt(arr[i], d);
            if (t < pivotChar) {
                swap(arr, lt++, i++);
            } else if (t > pivotChar) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }

        // Recursively sort the less-than and greater-than partitions for the same character index position
        sort(arr, low, lt - 1, d);
        
        // Advance the character index position ONLY for the equal-to partition
        if (pivotChar >= 0) {
            sort(arr, lt, gt, d + 1);
        }
        
        sort(arr, gt + 1, high, d);
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arr = {"she", "sells", "seashells", "by", "the", "sea", "shore"};
        sort(arr);
        System.out.println("Ternary Radix Tree Sorted: " + Arrays.toString(arr));
    }
}