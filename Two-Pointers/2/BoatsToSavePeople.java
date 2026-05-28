import java.util.Arrays;

/**
 * PROBLEM: Boats to Save People
 * * You are given an array people where people[i] is the weight of the ith person, 
 * and an infinite number of boats where each boat can carry a maximum weight of limit. 
 * Each boat carries at most two people at the same time, provided the sum of the weight 
 * of those people is at most limit.
 * Return the minimum number of boats to carry every given person.
 * * Example:
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * * Approach: Greedy Two Pointers. Sort the array, then try to pair the heaviest person 
 * with the lightest person.
 */
public class BoatsToSavePeople {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0; // Lightest person
        int right = people.length - 1; // Heaviest person
        int boats = 0;
        
        while (left <= right) {
            // If the lightest and heaviest can share a boat
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // Regardless of whether they shared or not, the heaviest person gets on a boat
            right--;
            boats++;
        }
        
        return boats;
    }

    public static void main(String[] args) {
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        System.out.println("Boats required: " + numRescueBoats(people, limit)); // 3
    }
}