public class PeakIndexMountainArray {
    public static int peakIndexInMountainArray(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If we are on the descending slope, the peak is to the left (or is mid)
            if (arr[mid] > arr[mid + 1]) {
                high = mid; 
            } 
            // If we are on the ascending slope, the peak is to the right
            else {
                low = mid + 1;
            }
        }
        // low and high converge to the peak
        return low; 
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 10, 5, 2};
        System.out.println("Peak index is: " + peakIndexInMountainArray(arr)); // 2
    }
}