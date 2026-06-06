import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Roman to Integer
 * * Convert a given Roman numeral string back into its corresponding integer format value.
 * * Strategy: Neighborhood Look-Ahead Parser
 * Traverse the string left-to-right. Map each token to its respective value. 
 * If the value of the current character is less than the value of the next character, 
 * subtract the current value from your running total (e.g., IV = -1 + 5 = 4). Otherwise, add it.
 */
public class RomanToInteger {
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);   map.put('V', 5);   map.put('X', 10);
        map.put('L', 50);  map.put('C', 100); map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            int currentVal = map.get(s.charAt(i));
            
            // Check if subtraction condition matches looking ahead to next neighbor
            if (i + 1 < len && currentVal < map.get(s.charAt(i + 1))) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("MMMDCCXLIII converted to integer: " + romanToInt("MMMDCCXLIII")); // 3743
    }
}