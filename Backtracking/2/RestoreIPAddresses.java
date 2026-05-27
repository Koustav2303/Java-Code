import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return result;
        backtrack(result, s, new StringBuilder(), 0, 0);
        return result;
    }

    private static void backtrack(List<String> result, String s, StringBuilder current, int index, int segmentCount) {
        if (segmentCount == 4 && index == s.length()) {
            // Remove the trailing dot and add to result
            result.add(current.substring(0, current.length() - 1));
            return;
        }
        if (segmentCount == 4 || index == s.length()) return;

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);
            
            // Validate the IP segment
            if (isValid(segment)) {
                int previousLength = current.length();
                current.append(segment).append(".");
                
                backtrack(result, s, current, index + len, segmentCount + 1);
                
                // Backtrack by restoring the StringBuilder to its previous length
                current.setLength(previousLength);
            }
        }
    }

    private static boolean isValid(String s) {
        if (s.length() > 1 && s.startsWith("0")) return false; // No leading zeros
        int val = Integer.parseInt(s);
        return val >= 0 && val <= 255;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println("Valid IPs: " + restoreIpAddresses(s));
    }
}