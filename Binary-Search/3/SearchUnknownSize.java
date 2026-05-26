public class SearchUnknownSize {
    // Mock interface to simulate the problem environment
    static class ArrayReader {
        private int[] arr;
        public ArrayReader(int[] arr) { this.arr = arr; }
        public int get(int index) { return index < arr.length ? arr[index] : Integer.MAX_VALUE; }
    }

    public static int search(ArrayReader reader, int target) {
        int low = 0;
        int high = 1;
        
        // Exponentially expand the bounds until the target is within range
        while (reader.get(high) < target) {
            low = high;
            high *= 2;
        }
        
        // Standard Binary Search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = reader.get(mid);
            
            if (val == target) return mid;
            if (val > target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[]{-1, 0, 3, 5, 9, 12});
        System.out.println("Target 9 is at index: " + search(reader, 9)); // 4
    }
}