import java.util.*;

public class CutOffTrees {
    public static int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        
        trees.sort((a, b) -> a[2] - b[2]);
        
        int totalSteps = 0;
        int sr = 0, sc = 0;
        
        for (int[] tree : trees) {
            int steps = bfs(forest, sr, sc, tree[0], tree[1]);
            if (steps == -1) return -1;
            totalSteps += steps;
            sr = tree[0];
            sc = tree[1];
        }
        return totalSteps;
    }
    
    private static int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        if (sr == tr && sc == tc) return 0;
        int m = forest.size(), n = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        
        queue.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        int steps = 0;
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == tr && curr[1] == tc) return steps;
                
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0], nc = curr[1] + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && forest.get(nr).get(nc) > 0) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(0, 0, 4),
            Arrays.asList(7, 6, 5)
        );
        System.out.println("Steps to cut all trees: " + cutOffTree(forest)); // 6
    }
}