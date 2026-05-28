import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EvaluateDivision {
    static class Edge {
        String dest;
        double weight;
        Edge(String d, double w) { dest = d; weight = w; }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<Edge>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];
            
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, val));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, 1.0 / val));
        }
        
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            results[i] = bfs(graph, queries.get(i).get(0), queries.get(i).get(1));
        }
        return results;
    }
    
    private static double bfs(HashMap<String, List<Edge>> graph, String start, String target) {
        if (!graph.containsKey(start) || !graph.containsKey(target)) return -1.0;
        if (start.equals(target)) return 1.0;
        
        Queue<Map.Entry<String, Double>> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        
        queue.add(new java.util.AbstractMap.SimpleEntry<>(start, 1.0));
        visited.add(start);
        
        while (!queue.isEmpty()) {
            Map.Entry<String, Double> curr = queue.poll();
            String node = curr.getKey();
            double value = curr.getValue();
            
            if (node.equals(target)) return value;
            
            for (Edge neighbor : graph.get(node)) {
                if (!visited.contains(neighbor.dest)) {
                    visited.add(neighbor.dest);
                    queue.add(new java.util.AbstractMap.SimpleEntry<>(neighbor.dest, value * neighbor.weight));
                }
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"));
        
        double[] results = calcEquation(equations, values, queries);
        System.out.println("Query Results: " + Arrays.toString(results)); // [6.0, 0.5]
    }
}