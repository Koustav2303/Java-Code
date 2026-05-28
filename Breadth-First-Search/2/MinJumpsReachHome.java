import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinJumpsReachHome {
    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        HashSet<Integer> forbiddenSet = new HashSet<>();
        for (int f : forbidden) forbiddenSet.add(f);
        
        // The upper bound is mathematically guaranteed to be ~6000
        int limit = 6000;
        boolean[][] visited = new boolean[limit][2]; // [position][jumped_backward?]
        Queue<int[]> queue = new LinkedList<>(); // {pos, jumps, jumped_backward?}
        
        queue.add(new int[]{0, 0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0], jumps = curr[1], isBackward = curr[2];
            
            if (pos == x) return jumps;
            
            // Try jumping forward
            int forward = pos + a;
            if (forward < limit && !forbiddenSet.contains(forward) && !visited[forward][0]) {
                visited[forward][0] = true;
                queue.add(new int[]{forward, jumps + 1, 0});
            }
            
            // Try jumping backward (only if we didn't just jump backward)
            int backward = pos - b;
            if (isBackward == 0 && backward >= 0 && !forbiddenSet.contains(backward) && !visited[backward][1]) {
                visited[backward][1] = true;
                queue.add(new int[]{backward, jumps + 1, 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] forbidden = {14, 4, 18, 1, 15};
        int a = 3, b = 15, x = 9;
        System.out.println("Min jumps to home: " + minimumJumps(forbidden, a, b, x)); // 3
    }
}