public class StringToInteger {
    public static int myAtoi(String s) {
        int index = 0, sign = 1, total = 0;
        
        // 1. Remove leading whitespace
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        
        if (index == s.length()) return 0; // Empty or just spaces

        // 2. Handle signs
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 3. Convert numbers and avoid overflow
        while (index < s.length()) {
            int digit = s.charAt(index) - '0'; // Convert char to int
            if (digit < 0 || digit > 9) break; // Break if non-digit found

            // Check if multiplying by 10 will cause an overflow
            if (Integer.MAX_VALUE / 10 < total || (Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }

    public static void main(String[] args) {
        String numStr = "   -42 with words";
        System.out.println("String input: \"" + numStr + "\"");
        System.out.println("Parsed Integer: " + myAtoi(numStr));
    }
}