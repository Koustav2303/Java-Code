/**
 * PROBLEM: String Compression
 * * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * - If the group's length is 1, append the character to s.
 * - Otherwise, append the character followed by the group's length.
 * You must write the compressed string back into the original char array and return its new length.
 * * Approach:
 * Use a read pointer and a write pointer to simulate the compression strictly in-place.
 */
public class StringCompression {
    public static int compress(char[] chars) {
        int write = 0;
        int read = 0;
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            // Count consecutive characters
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }
            
            // Write the character
            chars[write++] = currentChar;
            
            // Write the count if greater than 1
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        return write;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int newLen = compress(chars);
        System.out.print("Compressed Array: ");
        for (int i = 0; i < newLen; i++) System.out.print(chars[i] + " ");
        System.out.println("\nNew length: " + newLen); // 6 (a2b2c3)
    }
}