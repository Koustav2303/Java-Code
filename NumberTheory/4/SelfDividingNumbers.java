import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Self-Dividing Numbers
 * * A self-dividing number is a number that is divisible by every digit it contains.
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * Also, a self-dividing number is not allowed to contain the digit zero.
 * Given a lower and upper number bound, return a list of every valid matching number inside that interval.
 */
public class SelfDividingNumbers {
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (selfDivideCheck(i)) ans.add(i);
        }
        return ans;
    }

    private static boolean selfDivideCheck(int num) {
        int temp = num;
        while (temp > 0) {
            int digit = temp % 10;
            // Fail if digit is 0 or if it doesn't divide the original number evenly
            if (digit == 0 || num % digit != 0) return false;
            temp /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Self-dividing numbers [1, 22]: " + selfDividingNumbers(1, 22));
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
    }
}