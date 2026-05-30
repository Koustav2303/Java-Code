/**
 * PROBLEM: Find the Town Judge
 * * In a town, there are n people. There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * 1. The town judge trusts nobody.
 * 2. Everybody (except for the town judge) trusts the town judge.
 * Given trust[i] = [a, b] meaning a trusts b. Find the judge (if they exist).
 * * Approach:
 * Simulate graph degrees. A person gains 1 "trust point" if someone trusts them, 
 * and loses 1 "trust point" if they trust someone. 
 * The judge will have exactly (n - 1) trust points.
 */
public class FindTheTownJudge {
    public static int findJudge(int n, int[][] trust) {
        if (trust.length < n - 1) return -1; // Optimization: Graph edges must be at least N-1
        
        int[] trustScores = new int[n + 1];
        
        for (int[] relation : trust) {
            trustScores[relation[0]]--; // Trusts someone (loses a point)
            trustScores[relation[1]]++; // Is trusted (gains a point)
        }
        
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = {{1, 3}, {2, 3}};
        System.out.println("Town judge is: " + findJudge(3, trust)); // 3
    }
}