import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PROBLEM: Keys and Rooms
 * * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, 
 * denoting which room it unlocks.
 * Return true if you can visit all the rooms, or false otherwise.
 * * Approach:
 * Simple BFS/DFS reachability. We start at node 0 and traverse edges. 
 * If the number of unique visited nodes equals the total number of nodes, we win.
 */
public class KeysAndRooms {
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int visitedCount = 1;
        
        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();
            
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    queue.add(key);
                }
            }
        }
        
        return visitedCount == n;
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = Arrays.asList(
            Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), new ArrayList<>()
        );
        System.out.println("Can visit all rooms? " + canVisitAllRooms(rooms)); // true
    }
}