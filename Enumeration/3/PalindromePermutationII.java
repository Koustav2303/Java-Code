import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Palindrome Permutation II
 * * Given a string s, return all the palindromic permutations (case-sensitive) of it. 
 * If no palindromic permutation can be formed, return an empty list.
 * * Strategy: Permute Half, Then Mirror
 * A string can form a palindrome if and only if at most one character has an odd frequency count.
 * Isolate the odd character pivot (if any), collect half of the remaining character frequencies, 
 * run a unique permutation configuration generator over that half, and mirror the strings at the terminal step.
 */
public class PalindromePermutationII {
    public static List<String> generatePalindromes(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) counts[c]++;

        int odds = 0;
        String center = "";
        List<Character> pool = new ArrayList<>();

        for (int i = 0; i < 128; i++) {
            if (counts[i] % 2 != 0) {
                odds++;
                center = String.valueOf((char) i);
            }
            // Populate the target pool with half the instances of each character
            for (int j = 0; j < counts[i] / 2; j++) {
                pool.add((char) i);
            }
        }

        List<String> result = new ArrayList<>();
        if (odds > 1) return result; // Mathematically impossible to form a palindrome

        boolean[] used = new boolean[pool.size()];
        backtrack(pool, used, center, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(List<Character> pool, boolean[] used, String center, StringBuilder sb, List<String> result) {
        if (sb.length() == pool.size()) {
            // Mirror step: Construct full palindrome = (LeftHalf) + Center + (ReversedLeftHalf)
            String leftHalf = sb.toString();
            String rightHalf = sb.reverse().toString();
            sb.reverse(); // Restore sb structure back
            result.add(leftHalf + center + rightHalf);
            return;
        }

        for (int i = 0; i < pool.size(); i++) {
            if (used[i]) continue;
            // Standard unique permutation deduplication skip rule
            if (i > 0 && pool.get(i) == pool.get(i - 1) && !used[i - 1]) continue;

            used[i] = true;
            sb.append(pool.get(i));
            backtrack(pool, used, center, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Palindromic permutations for 'aabb': " + generatePalindromes("aabb")); // [abba, baab]
    }
}