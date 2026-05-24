public class StringCompression {
    public static String compress(String s) {
        if (s == null || s.length() == 0) return s;

        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;

        for (int i = 0; i < s.length(); i++) {
            countConsecutive++;

            // If we are at the end of the string, or the next character is different
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compressed.append(s.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0; // Reset counter for the next character
            }
        }

        // Only return the compressed string if it actually saves space
        return compressed.length() < s.length() ? compressed.toString() : s;
    }

    public static void main(String[] args) {
        String str = "aabcccccaaa";
        System.out.println("Original string: " + str);
        System.out.println("Compressed: " + compress(str));
        
        String shortStr = "abc";
        System.out.println("Original string: " + shortStr);
        System.out.println("Compressed (fallback to original): " + compress(shortStr));
    }
}