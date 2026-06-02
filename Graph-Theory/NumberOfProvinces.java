/**
 * PROBLEM: Number of Provinces
 * * There are n cities. Some of them are connected, while some are not. If city a is connected directly 
 * with city b, and city b with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city 
 * are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 * * Approach:
 * Implicit Graph DFS. Instead of an adjacency list, we traverse the adjacency matrix. 
 * If a city hasn't been visited, increment our province count and launch a DFS to visit 
 * all cities connected to it.
 */
public class NumberOfProvinces {
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(isConnected, visited, i, n);
            }
        }
        
        return provinces;
    }
    
    private static void dfs(int[][] isConnected, boolean[] visited, int city, int n) {
        visited[city] = true;
        for (int neighbor = 0; neighbor < n; neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor, n);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Number of Provinces: " + findCircleNum(isConnected)); // 2
    }
}