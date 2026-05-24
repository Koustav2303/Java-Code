import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ZeroOrOneLosses {
    public static List<List<Integer>> findWinners(int[][] matches) {
        HashMap<Integer, Integer> lossesCount = new HashMap<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // Ensure winner is in the map, but don't add to their losses
            lossesCount.putIfAbsent(winner, 0);
            
            // Add a loss to the loser
            lossesCount.put(loser, lossesCount.getOrDefault(loser, 0) + 1);
        }

        List<Integer> zeroLosses = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();

        for (int player : lossesCount.keySet()) {
            if (lossesCount.get(player) == 0) zeroLosses.add(player);
            else if (lossesCount.get(player) == 1) oneLoss.add(player);
        }

        // Problem usually requires sorted output
        Collections.sort(zeroLosses);
        Collections.sort(oneLoss);

        return Arrays.asList(zeroLosses, oneLoss);
    }

    public static void main(String[] args) {
        int[][] matches = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {4,9}, {10,4}, {10,9}};
        
        List<List<Integer>> results = findWinners(matches);
        System.out.println("Players with 0 losses: " + results.get(0));
        System.out.println("Players with exactly 1 loss: " + results.get(1));
    }
}