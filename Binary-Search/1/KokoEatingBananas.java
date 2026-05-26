public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1; // Min possible speed
        int high = 0; // Max possible speed (the largest pile)
        for (int pile : piles) high = Math.max(high, pile);

        while (low <= high) {
            int midSpeed = low + (high - low) / 2;
            
            if (canEatAll(piles, midSpeed, h)) {
                high = midSpeed - 1; // Try to find a slower, more optimal speed
            } else {
                low = midSpeed + 1;  // Too slow, we must eat faster
            }
        }
        return low;
    }

    private static boolean canEatAll(int[] piles, int speed, int h) {
        long hoursNeeded = 0;
        for (int pile : piles) {
            // Math.ceil equivalent: (pile + speed - 1) / speed
            hoursNeeded += (pile + speed - 1) / speed; 
        }
        return hoursNeeded <= h;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println("Minimum eating speed: " + minEatingSpeed(piles, h)); // 4
    }
}