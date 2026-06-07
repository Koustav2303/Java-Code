/**
 * PROBLEM: Angle Between Hands of a Clock
 * * Given two integers, hour and minutes, return the smaller angle formed between the hour and the minute hand.
 * * Strategy: Relative Angular Velocity Tracking
 * Calculate the precise positions of both hands relative to 12:00 (0°).
 * - The minute hand moves at a rate of 6° per minute.
 * - The hour hand moves at a rate of 30° per hour plus 0.5° per elapsed minute.
 * Find the absolute difference, then take the minimum of that value and 360° minus that value.
 * * Complexity:
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class AngleBetweenHands {
    public static double angleClock(int hour, int minutes) {
        // Calculate absolute position angles relative to 12:00 index point
        double minuteAngle = minutes * 6.0;
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;

        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360.0 - diff);
    }

    public static void main(String[] args) {
        System.out.println("Angle at 12:30 -> " + angleClock(12, 30) + "°"); // 165.0°
        System.out.println("Angle at 3:30 -> " + angleClock(3, 30) + "°");   // 75.0°
    }
}