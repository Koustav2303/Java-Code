public class PascalRow {
    public static void main(String[] args) {
        int n = 5; // Row 5
        long res = 1;
        System.out.print("Row " + n + ": ");
        for (int i = 0; i <= n; i++) {
            System.out.print(res + " ");
            res = res * (n - i) / (i + 1);
        }
    }
}