import java.util.*;

/**
 * PROBLEM: Course Schedule II
 * * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you 
 * must take course b first if you want to take course a.
 * * Return the ordering of courses you should take to finish all courses. If there are many 
 * valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * * Example:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * * Complexity:
 * Time Complexity: O(V + E) where V is numCourses and E is prerequisites length.
 * Space Complexity: O(V + E) for storing the adjacency list and tracking states.
 */
public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Step 1: Build the directed graph and calculate in-degrees
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        
        // Step 2: Push all nodes with 0 dependencies to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        // Step 3: Processes Kahn's Algorithm BFS
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[index++] = curr;
            
            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // If index != numCourses, a cycle was detected (impossible to complete all courses)
        return index == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result = findOrder(numCourses, prerequisites);
        System.out.println("Valid course schedule sequence: " + Arrays.toString(result));
    }
}