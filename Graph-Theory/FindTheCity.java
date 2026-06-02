import java.util.Arrays;

/**
 * PROBLEM: Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * * There are n cities numbered from 0 to n-1. You are given edges where edges[i] = [from, to, weight].
 * Return the city with the smallest number of cities that are reachable through some path and whose 
 * distance is at most distanceThreshold. If there are multiple such cities, return the city with the greatest number.
 * * Approach:
 * Floyd-Warshall Algorithm. Since the graph is small (n <= 100), we can find the All-Pairs Shortest Path 
 * in O(V^3) time. After computing the shortest distance between every pair of nodes, we just count 
 * how many reachable cities are within the threshold for each city.
 */
public class FindTheCity {
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        int INF = 10001; // Max edges 100 * max weight 10000
        
        for (int[] row : dist) Arrays.fill(row, INF);
        for (int i = 0; i < n; i++) dist[i][i] = 0;
        
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }
        
        // Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int resultCity = -1;
        int minReachable = n;
        
        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }
            if (reachable <= minReachable) {
                minReachable = reachable;
                resultCity = i; // Will naturally overwrite with the greater city index
            }
        }
        
        return resultCity;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,3}, {1,2,1}, {1,3,4}, {2,3,1}};
        int distanceThreshold = 4;
        System.out.println("City to choose: " + findTheCity(4, edges, distanceThreshold)); // 3
    }
}