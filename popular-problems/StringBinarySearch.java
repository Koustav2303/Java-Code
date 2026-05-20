import java.util.Arrays;

public class StringBinarySearch {
    public static int search(String[] arr, String target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            int comparisonResult = arr[mid].compareTo(target);

            if (comparisonResult == 0) return mid;
            if (comparisonResult < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        // The array MUST be sorted alphabetically for binary search to work
        String[] words = {"apple", "banana", "cherry", "grape", "mango", "peach", "zebra"};
        String target = "mango";
        
        System.out.println("Sorted String Array: " + Arrays.toString(words));
        int index = search(words, target);
        
        System.out.println("Word '" + target + "' found at index: " + index);
    }
}