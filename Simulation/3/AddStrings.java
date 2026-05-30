/**
 * PROBLEM: Add Strings
 * * Given two non-negative integers, num1 and num2 represented as string, return 
 * the sum of num1 and num2 as a string.
 * * Approach:
 * Simulate manual addition from right to left, keeping track of the carry.
 */
public class AddStrings {
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            
            i--;
            j--;
        }
        
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("11 + 123 = " + addStrings("11", "123")); // 134
    }
}