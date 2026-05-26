public class ExcelColumnNumber {
    public static int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            // Multiply by 26 for each position shift
            result *= 26;
            // Add the integer value of the current character (A=1, B=2, etc.)
            result += (columnTitle.charAt(i) - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        String col = "AB"; 
        System.out.println("Column '" + col + "' is number: " + titleToNumber(col));
        // A = 1, Z = 26, AA = 27, AB = 28
    }
}