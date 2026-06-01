import java.util.*;

public class CriticalConnections {
    private static int time = 0;
    
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> conn : connections) {
            adj.get(conn.get(0)).add(conn.get(1));
            adj.get(conn.get(1)).add(conn.get(0));
        }
        
        int[] discoveryTime = new int[n];
        int[] lowestTime = new int[n];
        Arrays.fill(discoveryTime, -1); 
        
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, discoveryTime, lowestTime, adj, bridges);
        return bridges;
    }
    
    private static void dfs(int node, int parent, int[] discoveryTime, int[] lowestTime, 
                            List<List<Integer>> adj, List<List<Integer>> bridges) {
        discoveryTime[node] = lowestTime[node] = ++time;
        
        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue; 
            
            if (discoveryTime[neighbor] == -1) { 
                dfs(neighbor, node, discoveryTime, lowestTime, adj, bridges);
                lowestTime[node] = Math.min(lowestTime[node], lowestTime[neighbor]);
                
                if (lowestTime[neighbor] > discoveryTime[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }
            } else {
                lowestTime[node] = Math.min(lowestTime[node], discoveryTime[neighbor]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = Arrays.asList(
            Arrays.asList(0, 1), Arrays.asList(1, 2), 
            Arrays.asList(2, 0), Arrays.asList(1, 3)
        );
        System.out.println("Critical connections: " + criticalConnections(4, connections)); // [[1, 3]]
    }
}