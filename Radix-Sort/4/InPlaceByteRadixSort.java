import java.util.Arrays;

/**
 * PROBLEM: In Place Byte Radix Sort
 * * Implement an in-place integer radix sort that does not require an auxiliary output array.
 * * Strategy: Cycle Permutation Sub-Byte Sieve
 * For each byte pass, compute the frequency counts and determine head/tail index boundaries for each bucket. 
 * Use cycle permutation to swap elements directly into their target bucket regions in-place, 
 * matching elements to their correct destination boundaries using a tracking array.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1) auxiliary space (reuses fixed 256-sized bucket lookup arrays).
 */
public class InPlaceByteRadixSort {
    public static void sortInPlace(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        // 4 passes for 32-bit integers (8 bits per byte pass)
        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];
            for (int i = 0; i < n; i++) count[(arr[i] >> shift) & 0xFF]++;

            int[] bucketHeads = new int[256];
            int[] bucketTails = new int[256];
            bucketHeads[0] = 0;
            bucketTails[0] = count[0] - 1;
            
            for (int i = 1; i < 256; i++) {
                bucketHeads[i] = bucketHeads[i - 1] + count[i - 1];
                bucketTails[i] = bucketHeads[i] + count[i] - 1;
            }

            // In-place cycle permutation loop
            for (int i = 0; i < 256; i++) {
                while (bucketHeads[i] <= bucketTails[i]) {
                    int currElement = arr[bucketHeads[i]];
                    int byteVal = (currElement >> shift) & 0xFF;

                    if (byteVal == i) {
                        bucketHeads[i]++; // Element already sits inside its target bucket region
                    } else {
                        // Swap the element into its correct bucket region
                        int destIndex = bucketHeads[byteVal];
                        arr[bucketHeads[i]] = arr[destIndex];
                        arr[destIndex] = currElement;
                        bucketHeads[byteVal]++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {234, 11, 9043, 2, 45, 990, 0, 15};
        sortInPlace(arr);
        System.out.println("In-Place Byte Permuted: " + Arrays.toString(arr));
    }
}