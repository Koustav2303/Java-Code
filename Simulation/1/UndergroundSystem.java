/**
 * PROBLEM: Design Underground System
 * * Implement the UndergroundSystem class:
 * - checkIn(int id, String stationName, int t): A customer with ID checks in at stationName at time t.
 * - checkOut(int id, String stationName, int t): A customer checks out at stationName at time t.
 * - getAverageTime(String startStation, String endStation): Returns the average time to travel.
 * * Approach:
 * Simulate the system using two HashMaps.
 * Map 1: Tracks active journeys (Passenger ID -> [Start Station, Start Time])
 * Map 2: Tracks completed routes ("StartStation->EndStation" -> [Total Time, Number of Trips])
 */

import java.util.HashMap;

public class UndergroundSystem {
    static class CheckInInfo {
        String stationName;
        int time;
        CheckInInfo(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    static class RouteStats {
        double totalTime;
        int tripCount;
    }

    private HashMap<Integer, CheckInInfo> checkIns;
    private HashMap<String, RouteStats> routeData;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routeData = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo startInfo = checkIns.remove(id); // Remove from active trips
        
        String routeKey = startInfo.stationName + "->" + stationName;
        int tripDuration = t - startInfo.time;
        
        routeData.putIfAbsent(routeKey, new RouteStats());
        RouteStats stats = routeData.get(routeKey);
        stats.totalTime += tripDuration;
        stats.tripCount++;
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        RouteStats stats = routeData.get(routeKey);
        return stats.totalTime / stats.tripCount;
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkOut(45, "Waterloo", 15); // Takes 12 time units
        System.out.println("Average time: " + undergroundSystem.getAverageTime("Leyton", "Waterloo")); // 12.0
    }
}