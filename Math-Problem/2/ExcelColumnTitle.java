public class ExcelColumnTitle {
    public static String convertToTitle(int columnNumber) {
        StringBuilder title = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--; // Adjust for 1-based indexing
            int remainder = columnNumber % 26;
            title.append((char) (remainder + 'A'));
            columnNumber /= 26;
        }
        
        // We appended from right to left, so reverse it
        return title.reverse().toString();
    }

    public static void main(String[] args) {
        int num = 701;
        System.out.println("Number " + num + " is column: " + convertToTitle(num));
        // 701 = ZY
    }
}