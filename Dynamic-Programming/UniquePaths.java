import java.util.Arrays;

public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[] row = new int[n];
        Arrays.fill(row, 1);
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                row[j] += row[j - 1]; // Current = Above + Left
            }
        }
        return row[n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println("Grid: " + m + "x" + n);
        System.out.println("Unique Paths: " + uniquePaths(m, n));
    }
}