import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeNeededToInform {
    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> subordinates = new ArrayList<>();
        for (int i = 0; i < n; i++) subordinates.add(new ArrayList<>());
        
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                subordinates.get(manager[i]).add(i);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>(); // {employeeID, timeReceived}
        queue.add(new int[]{headID, 0});
        
        int maxTime = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int id = curr[0], time = curr[1];
            
            maxTime = Math.max(maxTime, time);
            
            for (int sub : subordinates.get(id)) {
                queue.add(new int[]{sub, time + informTime[id]});
            }
        }
        
        return maxTime;
    }

    public static void main(String[] args) {
        int n = 6, headID = 2;
        int[] manager = {2, 2, -1, 2, 2, 2};
        int[] informTime = {0, 0, 1, 0, 0, 0};
        System.out.println("Total time to inform: " + numOfMinutes(n, headID, manager, informTime)); // 1
    }
}