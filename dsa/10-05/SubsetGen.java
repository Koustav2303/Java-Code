public class SubsetGen {
    public static void main(String[] args) {
        findSubsets("abc", "", 0);
    }

    public static void findSubsets(String s, String curr, int i) {
        if (i == s.length()) {
            System.out.print("[" + curr + "] ");
            return;
        }
        findSubsets(s, curr + s.charAt(i), i + 1); // Include
        findSubsets(s, curr, i + 1);               // Exclude
    }
}