/**
 * PROBLEM: Excel Sheet Column Title
 * * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 * A -> 1, B -> 2, ..., Z -> 26, AA -> 27, AB -> 28...
 * * Approach:
 * Simulate a Base-26 conversion. Because the system is 1-indexed (A=1, not 0), 
 * we must subtract 1 from the number before applying the modulo % 26 operation.
 */
public class ExcelSheetColumnTitle {
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--; // Adjust for 1-based indexing
            int remainder = columnNumber % 26;
            sb.append((char) (remainder + 'A'));
            columnNumber /= 26;
        }
        
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("Column 28: " + convertToTitle(28)); // AB
    }
}