import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Text Justification
 * * Given an array of strings words and a width maxWidth, format the text such that each line 
 * has exactly maxWidth characters and is fully (left and right) justified.
 * * Rules:
 * - Pack as many words as possible per line.
 * - Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * - Spaces should be distributed as evenly as possible. If uneven, left slots get more spaces.
 * - The last line of text should be left-justified, and no extra space is inserted between words.
 * * Approach:
 * Rigorous string simulation. Group words line by line, then calculate gaps, and distribute spaces using modulo math.
 */
public class TextJustification {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < words.length) {
            int lineStart = i;
            int lineLength = words[i].length();
            i++;
            
            // Greedily pack words into this line
            while (i < words.length && lineLength + 1 + words[i].length() <= maxWidth) {
                lineLength += 1 + words[i].length();
                i++;
            }
            
            StringBuilder sb = new StringBuilder();
            int gaps = i - lineStart - 1; // Number of gaps between words
            
            // If it's the last line or the line only has one word: Left justify
            if (i == words.length || gaps == 0) {
                for (int j = lineStart; j < i; j++) {
                    sb.append(words[j]);
                    if (j < i - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } 
            // Standard full justification
            else {
                int totalSpaces = maxWidth - lineLength + gaps; // Total spaces to distribute
                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps; // These go to the leftmost gaps
                
                for (int j = lineStart; j < i; j++) {
                    sb.append(words[j]);
                    if (j < i - 1) {
                        for (int s = 0; s < spacesPerGap + (j - lineStart < extraSpaces ? 1 : 0); s++) {
                            sb.append(" ");
                        }
                    }
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        for (String line : fullJustify(words, maxWidth)) {
            System.out.println("\"" + line + "\"");
        }
    }
}