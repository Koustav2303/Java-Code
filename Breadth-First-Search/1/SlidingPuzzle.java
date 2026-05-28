import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingPuzzle {
    public static int slidingPuzzle(int[][] board) {
        String target = "123450";
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) start.append(num);
        }
        
        // Precomputed valid swap indices for a 2x3 board flattened into a 1D string
        int[][] swapIndices = {
            {1, 3},       // 0 can swap with 1, 3
            {0, 2, 4},    // 1 can swap with 0, 2, 4
            {1, 5},       // 2 can swap with 1, 5
            {0, 4},       // 3 can swap with 0, 4
            {1, 3, 5},    // 4 can swap with 1, 3, 5
            {2, 4}        // 5 can swap with 2, 4
        };
        
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(start.toString());
        visited.add(start.toString());
        
        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) return moves;
                
                int zeroIndex = curr.indexOf('0');
                for (int swapIndex : swapIndices[zeroIndex]) {
                    String next = swapString(curr, zeroIndex, swapIndex);
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            moves++;
        }
        return -1;
    }
    
    private static String swapString(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        System.out.println("Minimum moves to solve: " + slidingPuzzle(board)); // 1
    }
}