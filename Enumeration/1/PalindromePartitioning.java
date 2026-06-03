import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Palindrome Partitioning
 * * Given a string s, partition s such that every substring of the partition is a palindrome. 
 * Return all possible palindrome partitionings of s.
 * * Example:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * * Strategy: Decomposition Enumeration with Substring Checks
 * Step through the string slicing out substrings. If the slice from the start pointer to the current index 
 * evaluates as a palindrome, accept that configuration branch, push it to our current partition stack, 
 * and recursively look for valid slices in the remainder of the string.
 * * Complexity:
 * Time Complexity: O(N * 2^N) - Worst case all characters are identical, yielding 2^N partition choices.
 * Space Complexity: O(N) tracking frames.
 */
public class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, String s, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // Check if slice segment is a valid palindrome symmetry
            if (isPalindrome(s, start, i)) {
                current.add(s.substring(start, i + 1));
                backtrack(i + 1, s, current, result); // Recurse onto remaining index balance
                current.remove(current.size() - 1); // Standard undo backtrack step
            }
        }
    }

    private static boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Palindrome partitions for 'aab':\n" + partition("aab"));
    }
}