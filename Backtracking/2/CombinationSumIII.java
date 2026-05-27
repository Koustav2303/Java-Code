import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int remain, int start) {
        if (tempList.size() == k && remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        // Prune if we exceed the allowed size or overshoot the sum
        if (tempList.size() > k || remain < 0) return;
        
        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            backtrack(result, tempList, k, remain - i, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int k = 3, n = 9;
        System.out.println("Combinations of " + k + " numbers summing to " + n + ": " + combinationSum3(k, n));
        // Output: [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
    }
}