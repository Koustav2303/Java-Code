/**
 * PROBLEM: Excel Sheet Column Number
 * * Given a string columnTitle that represents the column title as appears in an Excel sheet, 
 * return its corresponding column number. (e.g., "A" -> 1, "B" -> 2, "Z" -> 26, "AA" -> 27).
 * * Strategy: Base-26 Accumulation
 * Treat the string as a base-26 positional integer. Traverse left-to-right, multiplying your running total 
 * by 26 and adding the numerical offset value of the current character: `(c - 'A' + 1)`.
 * * Complexity:
 * Time Complexity: O(N) where N is the length of the string.
 * Space Complexity: O(1)
 */
public class ExcelSheetColumnNumber {
    public static int titleToNumber(String columnTitle) {
        int columnNumber = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            // Shift positional weight by 26 and append current digit value
            columnNumber = columnNumber * 26 + (c - 'A' + 1);
        }
        return columnNumber;
    }

    public static void main(String[] args) {
        System.out.println("Column index for 'A': " + titleToNumber("A"));   // 1
        System.out.println("Column index for 'AA': " + titleToNumber("AA")); // 27
        System.out.println("Column index for 'FXSHRXW': " + titleToNumber("FXSHRXW")); // 2147483647
    }
}