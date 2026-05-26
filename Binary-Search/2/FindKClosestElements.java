import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0;
        int high = arr.length - k;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Compare the distance of the element at 'mid' and 'mid + k' to x
            // If the right element is closer (or equally close but we want smaller), we move right
            if (x - arr[mid] > arr[mid + k] - x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = low; i < low + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;
        System.out.println(k + " closest elements to " + x + ": " + findClosestElements(arr, k, x)); 
        // [1, 2, 3, 4]
    }
}