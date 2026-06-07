/**
 * PROBLEM: Excel Sheet Column Title
 * * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 * (e.g., 1 -> A, 26 -> Z, 27 -> AA).
 * * Strategy: Bijective Base-26 Shifting
 * This mirrors base-26 radix conversion, but is 1-indexed rather than 0-indexed (there is no symbol for 0). 
 * To adjust for this offset, decrement the tracking number by 1 at each step before computing the remainder: 
 * `remainder = number % 26`. Append the matching character and repeat.
 * * Complexity:
 * Time Complexity: O(log_{26}(N))
 * Space Complexity: O(log_{26}(N)) for the string buffer.
 */
public class ExcelSheetColumnTitle {
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--; // Adjust for the 1-indexed bijective base-26 property
            int remainder = columnNumber % 26;
            sb.append((char) ('A' + remainder));
            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("Title for 1: " + convertToTitle(1));   // A
        System.out.println("Title for 28: " + convertToTitle(28)); // AB
        System.out.println("Title for 701: " + convertToTitle(701)); // ZY
    }
}