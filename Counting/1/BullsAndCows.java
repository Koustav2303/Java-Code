/**
 * PROBLEM: Bulls and Cows
 * * You are playing the Bulls and Cows game with your friend.
 * You write down a secret number and ask your friend to guess what the number is. 
 * - Bulls: Digits in the guess that are in the correct position.
 * - Cows: Digits in the guess that are in your secret number but are located in the wrong position.
 * Given the secret and guess, return the hint in the format "xAyB" (x bulls, y cows).
 * * Example:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B" (1 bull, 3 cows)
 * * Approach:
 * We use an array of size 10 to count digit frequencies.
 * If digits match perfectly, it's a Bull.
 * Otherwise, we increment the count for the secret digit and decrement for the guess digit.
 * If an incremented count was < 0, it means the guess previously asked for this digit (Cow!).
 * If a decremented count was > 0, it means the secret previously supplied this digit (Cow!).
 */
public class BullsAndCows {
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            
            if (s == g) {
                bulls++;
            } else {
                // Secret provides a digit (positive). If it was < 0, a guess already claimed it.
                if (numbers[s] < 0) cows++;
                // Guess requests a digit (negative). If it was > 0, the secret already provided it.
                if (numbers[g] > 0) cows++;
                
                numbers[s]++;
                numbers[g]--;
            }
        }
        
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        System.out.println("Hint for 1807 vs 7810: " + getHint("1807", "7810")); // 1A3B
    }
}