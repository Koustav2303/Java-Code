import java.util.Arrays;

public class MatchsticksToSquare {
    public static boolean makesquare(int[] matchsticks) {
        int perimeter = 0;
        for (int m : matchsticks) perimeter += m;
        if (perimeter == 0 || perimeter % 4 != 0) return false;
        
        Arrays.sort(matchsticks);
        reverse(matchsticks); // Sort descending to place the biggest matchsticks first
        
        return backtrack(matchsticks, new int[4], 0, perimeter / 4);
    }
    
    private static boolean backtrack(int[] matchsticks, int[] sides, int index, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target && sides[1] == target && sides[2] == target;
        }
        
        for (int i = 0; i < 4; i++) {
            // Pruning: if this matchstick breaks the target size, skip
            if (sides[i] + matchsticks[index] > target) continue;
            
            sides[i] += matchsticks[index];
            if (backtrack(matchsticks, sides, index + 1, target)) return true;
            sides[i] -= matchsticks[index]; // Backtrack
            
            // Pruning: If a side is 0 and it failed, subsequent sides will also fail
            if (sides[i] == 0) break;
        }
        return false;
    }
    
    private static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    public static void main(String[] args) {
        int[] matchsticks = {1, 1, 2, 2, 2};
        System.out.println("Can form square? " + makesquare(matchsticks)); // true
    }
}