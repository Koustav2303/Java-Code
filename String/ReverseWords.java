import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWords {
    public static String reverse(String s) {
        // Trim leading/trailing spaces and split by 1 or more spaces (regex "\\s+")
        String[] words = s.trim().split("\\s+");
        
        // Convert to a List and reverse it
        List<String> wordList = Arrays.asList(words);
        Collections.reverse(wordList);
        
        // Join the words back together with a single space
        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        String sentence = "  the sky   is blue  ";
        System.out.println("Original: \"" + sentence + "\"");
        System.out.println("Reversed: \"" + reverse(sentence) + "\"");
    }
}