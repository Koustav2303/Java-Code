public class StringRotation {
    public static boolean isRotation(String s1, String s2) {
        // Must be the same length and not empty
        if (s1.length() != s2.length() || s1.length() == 0) {
            return false;
        }
        
        // Concatenate s1 with itself
        String s1s1 = s1 + s1;
        
        // Check if s2 is a substring of the concatenated string
        return s1s1.contains(s2);
    }

    public static void main(String[] args) {
        String word1 = "waterbottle";
        String word2 = "erbottlewat";
        
        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println("Are they rotations? " + isRotation(word1, word2));
    }
}