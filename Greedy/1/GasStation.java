public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int currentSurplus = 0;
        int startIndex = 0;
        
        for (int i = 0; i < gas.length; i++) {
            int net = gas[i] - cost[i];
            totalSurplus += net;
            currentSurplus += net;
            
            // If we run out of gas, we can't start at `startIndex` or any station before `i`
            if (currentSurplus < 0) {
                startIndex = i + 1; // Try the next station
                currentSurplus = 0; // Reset tank
            }
        }
        
        // If total gas >= total cost, a solution mathematically must exist
        return totalSurplus >= 0 ? startIndex : -1;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("Starting station index: " + canCompleteCircuit(gas, cost));
    }
}