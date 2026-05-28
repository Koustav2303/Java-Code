import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OpenTheLock {
    public static int openLock(String[] deadends, String target) {
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        
        queue.add("0000");
        visited.add("0000");
        int turns = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) return turns;
                
                // Generate all 8 possible next states
                for (int j = 0; j < 4; j++) {
                    char c = curr.charAt(j);
                    String up = curr.substring(0, j) + (c == '9' ? 0 : c - '0' + 1) + curr.substring(j + 1);
                    String down = curr.substring(0, j) + (c == '0' ? 9 : c - '0' - 1) + curr.substring(j + 1);
                    
                    if (!dead.contains(up) && visited.add(up)) queue.add(up);
                    if (!dead.contains(down) && visited.add(down)) queue.add(down);
                }
            }
            turns++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println("Minimum turns to open: " + openLock(deadends, target)); // 6
    }
}