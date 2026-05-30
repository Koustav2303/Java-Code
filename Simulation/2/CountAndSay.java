/**
 * PROBLEM: Count and Say
 * * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1).
 * * Example:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1s = "21"
 * countAndSay(4) = say "21" = one 2, then one 1 = "1211"
 * * Approach:
 * Iteratively simulate the process n-1 times. Track consecutive identical characters and append 
 * the count + the character to a new StringBuilder.
 */
public class CountAndSay {
    public static String countAndSay(int n) {
        String result = "1";
        
        // Run the simulation n - 1 times
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(result.charAt(j - 1));
                    count = 1; // Reset count for the new character
                }
            }
            // Append the final block
            sb.append(count).append(result.charAt(result.length() - 1));
            
            result = sb.toString();
        }
        
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Count and Say (n=4): " + countAndSay(4)); // 1211
        System.out.println("Count and Say (n=5): " + countAndSay(5)); // 111221
    }
}