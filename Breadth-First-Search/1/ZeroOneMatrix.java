import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Initialize queue with all 0s, set 1s to a dummy max distance
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // If the new cell's recorded distance is greater than current + 1, update and queue it
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && mat[nr][nc] > mat[r][c] + 1) {
                    mat[nr][nc] = mat[r][c] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = {{0,0,0}, {0,1,0}, {1,1,1}};
        int[][] res = updateMatrix(mat);
        System.out.println("Distance Matrix:");
        for (int[] row : res) System.out.println(Arrays.toString(row));
    }
}