import java.util.PriorityQueue;

/**
 * PROBLEM: Kth Smallest Element in a Sorted Matrix
 * * Given an n x n matrix where each of the rows and columns are sorted in ascending order, 
 * return the kth smallest element in the matrix.
 * * Strategy: Row Tracker Min-Heap
 * Treat this as a variations of merging $N$ sorted lists. Push the first element of each row into a Min-Heap, 
 * along with its coordinates: `[val, row, col]`. Pop the smallest element, and if columns remain 
 * in that row, push the next sequential neighbor into the heap. Repeat $k$ times.
 * * Complexity:
 * Time Complexity: O(X log N) where X = Min(N, K).
 */
public class KthSmallestElementInSortedMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        
        // Min-heap tracking node values: [value, row_index, col_index]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Insert seed values from the first column of each row
        for (int i = 0; i < Math.min(n, k); i++) {
            minHeap.add(new int[]{matrix[i][0], i, 0});
        }

        int[] currentItem = null;
        while (k-- > 0 && !minHeap.isEmpty()) {
            currentItem = minHeap.poll();
            int r = currentItem[1];
            int c = currentItem[2];

            // If elements remain to the right in the same row, push the next column item
            if (c + 1 < n) {
                minHeap.add(new int[]{matrix[r][c + 1], r, c + 1});
            }
        }
        return currentItem[0];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
        };
        System.out.println("8th smallest element in matrix: " + kthSmallest(matrix, 8)); // 13
    }
}