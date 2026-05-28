import java.util.PriorityQueue;

public class TrappingRainWaterII {
    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m < 3 || n < 3) return 0;
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]); // {row, col, height}
        boolean[][] visited = new boolean[m][n];
        
        // Add all borders to the Min-Heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minHeap.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        
        int water = 0, currentMaxHeight = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            currentMaxHeight = Math.max(currentMaxHeight, cell[2]);
            
            for (int[] dir : dirs) {
                int r = cell[0] + dir[0], c = cell[1] + dir[1];
                if (r >= 0 && r < m && c >= 0 && c < n && !visited[r][c]) {
                    visited[r][c] = true;
                    // If the neighbor is strictly lower than the border, it traps water
                    if (heightMap[r][c] < currentMaxHeight) {
                        water += currentMaxHeight - heightMap[r][c];
                    }
                    minHeap.add(new int[]{r, c, heightMap[r][c]});
                }
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[][] heights = {
            {1,4,3,1,3,2},
            {3,2,1,3,2,4},
            {2,3,3,2,3,1}
        };
        System.out.println("Total trapped water: " + trapRainWater(heights)); // 4
    }
}