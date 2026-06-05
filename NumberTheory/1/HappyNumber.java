/**
 * PROBLEM: Happy Number
 * * Write an algorithm to determine if a number n is happy. A happy number eventually reaches 1 
 * when replaced by the sum of the squares of its digits. If it loops endlessly in a cycle 
 * that does not include 1, it is unhappy.
 * * Strategy: Floyd's Cycle Detection Engine
 * Instead of maintaining a bulky HashSet of previously seen values, treat this value stream 
 * as an implicit linked list structure. Use a slow and fast pointer strategy to check for cycles 
 * in O(1) space. If they meet at a value other than 1, a cycle exists.
 * * Complexity:
 * Time Complexity: O(log(N))
 * Space Complexity: O(1) constant tracking memory footprint.
 */
public class HappyNumber {
    private static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            totalSum += d * d;
            n /= 10;
        }
        return totalSum;
    }

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);         // Step 1 node forward
            fast = getNext(getNext(fast)); // Step 2 nodes forward
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        System.out.println("Is 19 a happy number? " + isHappy(19)); // true
        System.out.println("Is 2 a happy number? " + isHappy(2));   // false
    }
}