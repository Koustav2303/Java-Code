public class AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';

            result.append(sum % 2); // The binary digit
            carry = sum / 2;        // The carry over
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String bin1 = "1010"; // 10
        String bin2 = "1011"; // 11
        System.out.println(bin1 + " + " + bin2 + " = " + addBinary(bin1, bin2)); // Expected: 10101 (21)
    }
}