/**
 * PROBLEM: Multiply Strings
 * * Given two non-negative integers represented as strings num1 and num2, return the product 
 * of num1 and num2, also represented as a string. Do not use any BigInteger libraries.
 * * Strategy: Elementary Long Multiplication Lattice
 * Maintain an integer array of size `len1 + len2` to capture intermediate digit products. 
 * Multiply digits from right to left. The product of `num1.charAt(i)` and `num2.charAt(j)` maps 
 * to position indices `i + j` (for the carry) and `i + j + 1` (for the single digit residue) in your array.
 */
public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";

        int n1 = num1.length(), n2 = num2.length();
        int[] pos = new int[n1 + n2];

        // Process digit pairs backwards
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;     // High position index (Carry target)
                int p2 = i + j + 1; // Low position index (Residue target)

                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : pos) {
            // Skip leading zeros
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("123 * 456 = " + multiply("123", "456")); // 56088
    }
}