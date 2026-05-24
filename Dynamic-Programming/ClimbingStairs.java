public class ClimbingStairs {
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int oneStepBefore = 2, twoStepsBefore = 1, allWays = 0;
        
        for (int i = 3; i <= n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
        }
        return allWays;
    }

    public static void main(String[] args) {
        int steps = 5;
        System.out.println("Ways to climb " + steps + " stairs: " + climbStairs(steps));
    }
}