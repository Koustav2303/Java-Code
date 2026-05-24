import java.util.HashSet;

public class HappyNumber {
    public static boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();
        
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        
        return n == 1; // If it's 1, it's happy. If it stopped because of a loop, it's not.
    }

    private static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int digit = n % 10;
            totalSum += digit * digit;
            n /= 10;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int num = 19;
        System.out.println("Is " + num + " a happy number? " + isHappy(num));
        
        int sadNum = 2;
        System.out.println("Is " + sadNum + " a happy number? " + isHappy(sadNum));
    }
}