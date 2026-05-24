public class LongestCommonPrefix {
    public static String longestPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // Start by assuming the first word is the complete prefix
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // While the current word does NOT start with the prefix
            while (strs[i].indexOf(prefix) != 0) {
                // Chop the last character off the prefix and try again
                prefix = prefix.substring(0, prefix.length() - 1);
                
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] words = {"flower", "flow", "flight"};
        System.out.println("Words: [flower, flow, flight]");
        System.out.println("Longest Common Prefix: " + longestPrefix(words));
    }
}