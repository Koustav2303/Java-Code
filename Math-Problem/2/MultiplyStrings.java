public class MultiplyStrings {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        
        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];
        
        // Multiply each digit from right to left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                
                int p1 = i + j;       // Tens position
                int p2 = i + j + 1;   // Ones position
                
                int sum = mul + result[p2];
                
                result[p2] = sum % 10;
                result[p1] += sum / 10; // Carry over
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int val : result) {
            // Skip leading zeroes
            if (!(sb.length() == 0 && val == 0)) {
                sb.append(val);
            }
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(num1 + " * " + num2 + " = " + multiply(num1, num2));
    }
}