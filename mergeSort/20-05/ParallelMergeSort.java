import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {
    static class SortTask extends RecursiveAction {
        private int[] arr;
        private int left, right;

        public SortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = left + (right - left) / 2;
                SortTask leftTask = new SortTask(arr, left, mid);
                SortTask rightTask = new SortTask(arr, mid + 1, right);
                
                // Run both halves in parallel
                invokeAll(leftTask, rightTask);
                
                merge(arr, left, mid, right);
            }
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] numbers = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new SortTask(numbers, 0, numbers.length - 1));
        
        System.out.println("Parallel Sorted: " + Arrays.toString(numbers));
    }
}