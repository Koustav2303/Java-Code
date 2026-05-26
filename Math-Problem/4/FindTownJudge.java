public class FindTownJudge {
    public static int findJudge(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) return 1;
        
        int[] trustScores = new int[n + 1];
        
        for (int[] t : trust) {
            trustScores[t[0]]--; // Person trusts someone, cannot be judge
            trustScores[t[1]]++; // Person is trusted by someone
        }
        
        // The judge will have a trust score of exactly N - 1
        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] trust = {{1, 3}, {2, 3}};
        System.out.println("The town judge is person: " + findJudge(n, trust));
    }
}