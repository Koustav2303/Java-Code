import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Binary Watch
 * * A binary watch has 4 LEDs on the top to represent hours (1, 2, 4, 8) and 6 LEDs on the bottom 
 * to represent minutes (1, 2, 4, 8, 16, 32). Each LED represents a zero or one, with the least significant bit on the right.
 * Given an integer turnedOn which represents the number of LEDs that are currently on, return all possible times 
 * the watch could demonstrate. You may return the answer in any order.
 * * Strategy: Structural Bit List Backtracking
 * We represent the 10 LEDs as an array of weights. We backtrack through the 10 positions. 
 * If an LED is chosen, update hour or minute accumulations. Prune branches where hours >= 12 or minutes >= 60.
 */
public class BinaryWatch {
    // Array map values: First 4 track hour weights, next 6 track minute weights
    private static final int[] WEIGHTS = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        backtrack(0, 0, 0, turnedOn, result);
        return result;
    }

    private static void backtrack(int index, int hours, int minutes, int remainingLEDs, List<String> result) {
        // Prune overflow boundaries immediately
        if (hours >= 12 || minutes >= 60) return;

        if (remainingLEDs == 0) {
            result.add(String.format("%d:%02d", hours, minutes));
            return;
        }
        if (index == WEIGHTS.length) return;

        // Choice 1: Turn ON the LED at the current index position
        if (index < 4) {
            backtrack(index + 1, hours + WEIGHTS[index], minutes, remainingLEDs - 1, result);
        } else {
            backtrack(index + 1, hours, minutes + WEIGHTS[index], remainingLEDs - 1, result);
        }

        // Choice 2: Keep the LED at the current index position turned OFF
        backtrack(index + 1, hours, minutes, remainingLEDs, result);
    }

    public static void main(String[] args) {
        System.out.println("Possible watch times for 1 LED: " + readBinaryWatch(1));
    }
}