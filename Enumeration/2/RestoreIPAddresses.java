import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Restore IP Addresses
 * * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 
 * inclusive and cannot have leading zeros.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
 * * Strategy: 3-Tier Length Choice Pruning
 * Slices the string into segments of lengths 1, 2, or 3. Ensure each isolated segment matches valid numerical boundaries 
 * (<= 255) and catch invalid leading zero scenarios (e.g. "01" is invalid but "0" is valid).
 */
public class RestoreIPAddresses {
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return result;
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, String s, List<String> segments, List<String> result) {
        if (segments.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }

        // Loop through candidate segment split options up to length 3
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break;
            
            String segment = s.substring(start, start + len);
            
            // Check for leading zero rule or value threshold breach
            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255) {
                continue;
            }

            segments.add(segment);
            backtrack(start + len, s, segments, result);
            segments.remove(segments.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Valid restored combinations: " + restoreIpAddresses("25525511135"));
        // ["255.255.11.135", "255.255.111.35"]
    }
}