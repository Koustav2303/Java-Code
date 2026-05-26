import java.util.HashMap;

public class RomanToInteger {
    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);   map.put('V', 5);   map.put('X', 10);
        map.put('L', 50);  map.put('C', 100); map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prevValue = 0;

        // Traverse from right to left for easier math
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = map.get(s.charAt(i));
            
            if (currentValue < prevValue) {
                total -= currentValue; // Subtraction case (e.g., IV, IX)
            } else {
                total += currentValue; // Normal addition case
            }
            prevValue = currentValue;
        }
        
        return total;
    }

    public static void main(String[] args) {
        String roman = "MCMXCIV"; // 1994
        System.out.println("Roman numeral: " + roman);
        System.out.println("Integer value: " + romanToInt(roman));
    }
}