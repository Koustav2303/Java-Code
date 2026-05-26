public class IntegerToRoman {
    public static String intToRoman(int num) {
        // Parallel arrays for values and symbols (including subtraction cases)
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            // Keep subtracting the largest possible value
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }
        
        return roman.toString();
    }

    public static void main(String[] args) {
        int year = 2024;
        System.out.println("Integer: " + year);
        System.out.println("Roman Numeral: " + intToRoman(year));
    }
}