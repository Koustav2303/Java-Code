import java.util.HashMap;

public class FractionToDecimal {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        
        StringBuilder result = new StringBuilder();
        
        // Determine the sign (XOR checks if signs are different)
        if (numerator < 0 ^ denominator < 0) {
            result.append("-");
        }
        
        // Convert to long to prevent overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Append the integer part
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0) return result.toString();
        
        // Append the decimal part
        result.append(".");
        HashMap<Long, Integer> remainderMap = new HashMap<>();
        
        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                // Repeating remainder found! Insert '(' at the recorded index and ')' at the end
                result.insert(remainderMap.get(remainder), "(");
                result.append(")");
                break;
            }
            
            remainderMap.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        int num = 4, den = 333; // 0.(012)
        System.out.println(num + " / " + den + " = " + fractionToDecimal(num, den));
    }
}