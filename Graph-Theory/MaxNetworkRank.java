/**
 * PROBLEM: Maximal Network Rank
 * * There is an infrastructure of n cities with some number of roads connecting these cities.
 * The network rank of two different cities is defined as the total number of directly connected 
 * roads to EITHER city. If a road is directly connected to both cities, it is only counted once.
 * Return the maximal network rank of the entire infrastructure.
 * * Approach:
 * Calculate the degree (number of edges) of each node.
 * Iterate through every pair of nodes (i, j). The network rank is `degree[i] + degree[j]`.
 * If an edge exists exactly between `i` and `j`, subtract 1 from the rank to avoid double counting.
 */
public class MaxNetworkRank {
    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        boolean[][] connected = new boolean[n][n];
        
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
            connected[road[0]][road[1]] = true;
            connected[road[1]][road[0]] = true;
        }
        
        int maxRank = 0;
        
        // Check every pair of cities
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentRank = degree[i] + degree[j];
                
                // Subtract the shared road if it exists
                if (connected[i][j]) {
                    currentRank--;
                }
                
                maxRank = Math.max(maxRank, currentRank);
            }
        }
        
        return maxRank;
    }

    public static void main(String[] args) {
        int[][] roads = {{0,1}, {0,3}, {1,2}, {1,3}};
        System.out.println("Maximal network rank: " + maximalNetworkRank(4, roads)); // 4 (cities 0 and 1)
    }
}