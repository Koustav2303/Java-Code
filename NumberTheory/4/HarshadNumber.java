/**
 * PROBLEM: Harshad Number
 * * An integer number in base 10 which is divisible by the sum of its digits is said to be a Harshad number.
 * Given an integer n, return the sum of its digits if it is a Harshad number, otherwise return -1.
 * * Strategy: Digit Accumulation
 * Extract and sum each digit using standard base-10 modulo loops. 
 * Then evaluate the division operation: $n \pmod{\text{digitSum}} == 0$.
 */
public class HarshadNumber {
    public static int sumOfTheDigitsOfHarshadNumber(int n) {
        int sum = 0;
        int temp = n;

        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }

        return (n % sum == 0) ? sum : -1;
    }

    public static void main(String[] args) {
        System.out.println("Result for 18: " + sumOfTheDigitsOfHarshadNumber(18)); // 9 (18 % 9 == 0)
        System.out.println("Result for 23: " + sumOfTheDigitsOfHarshadNumber(23)); // -1
    }
}