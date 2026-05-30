/**
 * PROBLEM: Zigzag Conversion
 * * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * * Approach:
 * Create an array of StringBuilders, one for each row.
 * Simulate reading the string left to right, walking "down" the array of StringBuilders, 
 * and then reversing direction to walk "up" when you hit the top or bottom boundary.
 */
public class ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        
        int currentRow = 0;
        boolean goingDown = false;
        
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            
            // If we hit the top or bottom row, flip the direction
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            currentRow += goingDown ? 1 : -1;
        }
        
        // Combine all rows
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println("Zigzag conversion: " + convert(s, numRows)); // PAHNAPLSIIGYIR
    }
}