import java.util.Arrays;

public class PlusOne {
    public static int[] plusOne(int[] digits) {
        // Traverse the array from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No carry needed, we are done
            }
            digits[i] = 0; // If it was 9, it becomes 0 and carries over
        }
        
        // If we made it through the whole loop, it means the number was like 999
        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;
        // The rest of the array defaults to 0 in Java, which is exactly what we want (1000)
        return newNumber;
    }

    public static void main(String[] args) {
        int[] digits = {4, 3, 2, 9};
        System.out.println("Original: " + Arrays.toString(digits));
        System.out.println("Plus One: " + Arrays.toString(plusOne(digits)));
    }
}