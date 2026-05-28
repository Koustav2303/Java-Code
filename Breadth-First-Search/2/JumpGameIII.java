import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII {
    public static boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (arr[curr] == 0) return true;
            
            int jumpRight = curr + arr[curr];
            int jumpLeft = curr - arr[curr];
            
            if (jumpRight < arr.length && !visited[jumpRight]) {
                visited[jumpRight] = true;
                queue.add(jumpRight);
            }
            if (jumpLeft >= 0 && !visited[jumpLeft]) {
                visited[jumpLeft] = true;
                queue.add(jumpLeft);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        System.out.println("Can reach 0? " + canReach(arr, start)); // true
    }
}