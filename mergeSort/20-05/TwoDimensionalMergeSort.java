import java.util.Arrays;

public class TwoDimensionalMergeSort {
    public static void merge(int[][] arr, int left, int mid, int right) {
        int[][] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[][] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            // Sorting based on the first column (index 0)
            if (L[i][0] <= R[j][0]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(int[][] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[][] intervals = {{5, 8}, {1, 3}, {9, 12}, {2, 6}};
        
        System.out.print("Original Intervals: ");
        System.out.println(Arrays.deepToString(intervals));
        
        sort(intervals, 0, intervals.length - 1);
        
        System.out.print("Sorted by start time: ");
        System.out.println(Arrays.deepToString(intervals));
    }
}