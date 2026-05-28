import java.util.*;

public class JumpGameIV {
    public static int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(0);
        visited[0] = true;
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n - 1) return steps;
                
                // Jump to same values
                List<Integer> nextIndices = graph.get(arr[curr]);
                if (nextIndices != null) {
                    for (int next : nextIndices) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.add(next);
                        }
                    }
                    // Crucial optimization to prevent checking the same value group repeatedly
                    graph.remove(arr[curr]); 
                }
                
                // Jump left/right
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true; queue.add(curr + 1);
                }
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true; queue.add(curr - 1);
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println("Min jumps: " + minJumps(arr)); // 3
    }
}