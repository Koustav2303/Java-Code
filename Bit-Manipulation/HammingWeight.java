public class HammingWeight {
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Clears the lowest set bit
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int number = 11; // Binary: 00000000000000000000000000001011 (Three 1s)
        System.out.println("Number: " + number);
        System.out.println("Number of 1 bits: " + hammingWeight(number));
    }
}