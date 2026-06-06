/**
 * PROBLEM: Integer to Roman
 * * Convert a given integer into its corresponding Roman numeral string representation.
 * * Strategy: Decreasing Weight Matrix Alignment
 * Store Roman numeral characters alongside their integer weight values in descending order. 
 * Iterate through your mapping arrays, greedily subtracting the largest possible weight 
 * from your total while appending its matching Roman token to your string builder.
 */
public class IntegerToRoman {
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            // Greedily consume the largest possible numerical blocks
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("3743 in Roman numerals: " + intToRoman(3743)); // MMMDCCXLIII
        System.out.println("58 in Roman numerals: " + intToRoman(58));     // LVIII
    }
}