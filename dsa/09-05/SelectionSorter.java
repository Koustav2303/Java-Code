import java.util.Arrays;

public class SelectionSorter {
    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13};
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Sorted: " + Arrays.toString(arr));
    }
}