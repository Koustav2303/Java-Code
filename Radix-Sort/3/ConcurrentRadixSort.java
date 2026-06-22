import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * PROBLEM: Concurrent Parallel Radix Sort
 * * Accelerate a 32-bit integer radix sort by parallelizing the execution across multicore processors.
 * * Strategy: Divide-and-Conquer Histogram Aggregation
 * Subdivide the array into equal regions across workers using a ForkJoin task model. 
 * Each thread computes local histograms for the current byte pass. Merge these arrays globally 
 * into a single unified layout to determine precise scatter offsets.
 */
public class ConcurrentRadixSort {
    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();

    public static void parallelSort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];

        for (int shift = 0; shift < 32; shift += 8) {
            int[][] localHistograms = new int[POOL.getParallelism() + 1][256];
            POOL.invoke(new HistogramTask(arr, shift, 0, n, localHistograms, 0));

            int[] globalCount = new int[256];
            for (int i = 0; i < localHistograms.length; i++) {
                for (int j = 0; j < 256; j++) {
                    globalCount[j] += localHistograms[i][j];
                }
            }

            int[] offsets = new int[256];
            for (int i = 1; i < 256; i++) {
                offsets[i] = offsets[i - 1] + globalCount[i - 1];
            }

            // Scatter the values based on our aggregated offsets
            for (int i = 0; i < n; i++) {
                int byteVal = (arr[i] >> shift) & 0xFF;
                output[offsets[byteVal]++] = arr[i];
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    private static class HistogramTask extends RecursiveAction {
        private final int[] arr;
        private final int shift, low, high, workerId;
        private final int[][] localHistograms;
        private static final int THRESHOLD = 8192;

        HistogramTask(int[] arr, int s, int l, int h, int[][] lh, int id) {
            this.arr = arr; this.shift = s; this.low = l; this.high = h; this.localHistograms = lh; this.workerId = id;
        }

        @Override
        protected void compute() {
            if (high - low <= THRESHOLD) {
                for (int i = low; i < high; i++) {
                    localHistograms[workerId][(arr[i] >> shift) & 0xFF]++;
                }
            } else {
                int mid = low + (high - low) / 2;
                invokeAll(
                    new HistogramTask(arr, shift, low, mid, localHistograms, workerId),
                    new HistogramTask(arr, shift, mid, high, localHistograms, workerId + 1)
                );
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {8932, 12, 43, 0, 104323, 492, 12, 943};
        parallelSort(arr);
        System.out.println("Concurrently Sorted: " + Arrays.toString(arr));
    }
}