/**
 * PROBLEM: Multiply Strings
 * * Given two non-negative integers num1 and num2 represented as strings, return the 
 * product of num1 and num2, also represented as a string.
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * * Approach:
 * Simulate manual elementary school multiplication.
 * The product of num1[i] and num2[j] will be placed at indices [i+j] and [i+j+1] in the result array.
 */
public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        
        int m = num1.length(), n = num2.length();
        int[] vals = new int[m + n];
        
        // Simulate right-to-left multiplication
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                
                int sum = mul + vals[p2]; // Add to existing value at this position
                
                vals[p1] += sum / 10; // Carry over
                vals[p2] = sum % 10;  // Remainder
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int val : vals) {
            // Skip leading zeros
            if (!(sb.length() == 0 && val == 0)) {
                sb.append(val);
            }
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("123 * 456 = " + multiply("123", "456")); // 56088
    }
}