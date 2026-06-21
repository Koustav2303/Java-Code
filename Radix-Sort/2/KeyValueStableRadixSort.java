import java.util.Arrays;

/**
 * PROBLEM: Key-Value Stable Radix Sort
 * * Sort an array of complex data records containing an integer key and an associated String payload, 
 * ensuring that records with matching keys retain their original relative order.
 * * Strategy: stable Position-Mapping Counting Pass
 * Run a base-10 LSD radix sort pass on the object keys. To maintain stability, the counting sort algorithm 
 * must extract entries from the source collection backwards, placement indices must decrease, and the 
 * payloads must be moved alongside their respective keys.
 */
public class KeyValueStableRadixSort {
    static class Record {
        int key; String payload;
        Record(int k, String p) { this.key = k; this.payload = p; }
        @Override public String toString() { return "(" + key + ":" + payload + ")"; }
    }

    public static void sortRecords(Record[] arr) {
        int max = arr[0].key;
        for (Record r : arr) if (r.key > max) max = r.key;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            Record[] output = new Record[arr.length];
            int[] count = new int[10];

            for (int i = 0; i < arr.length; i++) {
                count[(arr[i].key / exp) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Unwind backwards to maintain original relative ordering for matching keys
            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = (arr[i].key / exp) % 10;
                output[count[digit] - 1] = arr[i];
                count[digit]--;
            }

            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        Record[] records = {
            new Record(25, "First"),
            new Record(4,  "Apple"),
            new Record(25, "Second"),
            new Record(12, "Banana")
        };
        sortRecords(records);
        System.out.println("Stable Key-Value Pairs Output: " + Arrays.toString(records));
        // (25:First) is guaranteed to stay ahead of (25:Second)
    }
}