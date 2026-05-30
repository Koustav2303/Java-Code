/**
 * PROBLEM: Teemo Attacking
 * * Our hero Teemo is attacking an enemy Ashe with poison attacks. When Teemo attacks at time t, 
 * Ashe is poisoned for exactly 'duration' seconds (from t to t + duration - 1). 
 * If Teemo attacks again BEFORE the poison effect ends, the timer is RESET, and the poison effect 
 * will end 'duration' seconds after the new attack.
 * Given a non-decreasing integer array timeSeries and an integer duration, return the total 
 * number of seconds Ashe is poisoned.
 * * Approach:
 * Simulate the timeline. For every attack, Ashe is poisoned for the full duration, UNLESS 
 * the next attack happens before this duration finishes. 
 * Thus, we simply add the minimum between the 'duration' and the time gap to the next attack.
 */
public class TeemoAttacking {
    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        
        int totalPoisonTime = 0;
        
        for (int i = 0; i < timeSeries.length - 1; i++) {
            int timeGap = timeSeries[i + 1] - timeSeries[i];
            
            // Add whichever is shorter: the full duration, or the time until the next attack
            totalPoisonTime += Math.min(duration, timeGap);
        }
        
        // The last attack always applies the full duration
        totalPoisonTime += duration;
        
        return totalPoisonTime;
    }

    public static void main(String[] args) {
        int[] timeSeries = {1, 4};
        int duration = 2;
        System.out.println("Total poisoned duration: " + findPoisonedDuration(timeSeries, duration)); 
        // 4 (Seconds 1,2 and 4,5)
    }
}