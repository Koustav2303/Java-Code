import java.util.Arrays;

public class MinCostClimbingStairs {
    public static int minCost(int[] cost) {
        int step1 = 0, step2 = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            int currentStep = cost[i] + Math.min(step1, step2);
            step2 = step1;
            step1 = currentStep;
        }
        return Math.min(step1, step2);
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println("Cost array: " + Arrays.toString(cost));
        System.out.println("Min cost to reach top: " + minCost(cost));
    }
}