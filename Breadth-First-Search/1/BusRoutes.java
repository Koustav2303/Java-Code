import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BusRoutes {
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        
        // Map: Stop -> List of Bus Route Indices that visit it
        HashMap<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, x -> new ArrayList<>()).add(i);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visitedRoutes = new HashSet<>();
        
        // Initialize queue with all routes that visit the source stop
        for (int route : stopToRoutes.getOrDefault(source, new ArrayList<>())) {
            queue.add(route);
            visitedRoutes.add(route);
        }
        
        int busCount = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currRoute = queue.poll();
                
                for (int stop : routes[currRoute]) {
                    if (stop == target) return busCount;
                    
                    for (int nextRoute : stopToRoutes.get(stop)) {
                        if (visitedRoutes.add(nextRoute)) {
                            queue.add(nextRoute);
                        }
                    }
                }
            }
            busCount++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1, target = 6;
        System.out.println("Min buses needed: " + numBusesToDestination(routes, source, target)); // 2
    }
}