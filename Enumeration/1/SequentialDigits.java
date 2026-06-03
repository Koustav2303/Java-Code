import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PROBLEM: Sequential Digits
 * * An integer has sequential digits if each digit in the number is exactly 1 more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * * Example:
 * Input: low = 100, high = 300
 * Output: [123,234]
 * * Strategy: Structured Seed Property Enumeration
 * Since the pattern is strictly constant, do not use parsing backtracking. Explicitly enumerate numbers 
 * starting from each base single digit seed (1 to 9). Progressively expand values by multiplying by 10 
 * and adding the subsequent increment value.
 * * Complexity:
 * Time Complexity: O(1) - The absolute global solution space pool size is bounded by 36 possible numbers.
 * Space Complexity: O(1)
 */
public class SequentialDigits {
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        
        // Enumerate every starting seed choice digit 1 through 9
        for (int i = 1; i <= 9; i++) {
            int num = i;
            int nextDigit = i + 1;
            
            while (num <= high && nextDigit <= 9) {
                num = num * 10 + nextDigit; // Shift left and append next valid digit value
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
                nextDigit++;
            }
        }
        
        Collections.sort(result); // Return sorted order constraints
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Sequential digits between 100 and 300: " + sequentialDigits(100, 300)); // [123, 234]
    }
}