import java.util.*;

public class ReconstructItinerary {
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> flightMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            flightMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        
        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", flightMap, itinerary);
        return itinerary;
    }
    
    private static void dfs(String airport, Map<String, PriorityQueue<String>> flightMap, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = flightMap.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            dfs(destinations.poll(), flightMap, itinerary);
        }
        itinerary.addFirst(airport);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(
            Arrays.asList("JFK", "SFO"),
            Arrays.asList("JFK", "ATL"),
            Arrays.asList("SFO", "ATL"),
            Arrays.asList("ATL", "JFK"),
            Arrays.asList("ATL", "SFO")
        );
        System.out.println("Itinerary: " + findItinerary(tickets)); 
        // [JFK, ATL, JFK, SFO, ATL, SFO]
    }
}