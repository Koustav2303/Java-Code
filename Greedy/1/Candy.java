import java.util.Arrays;

public class Candy {
    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1); // Everyone gets at least 1 candy
        
        // Left-to-Right pass: check against left neighbor
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // Right-to-Left pass: check against right neighbor
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // Must satisfy both left and right conditions
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        int totalCandies = 0;
        for (int candy : candies) totalCandies += candy;
        
        return totalCandies;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        System.out.println("Minimum candies required: " + candy(ratings)); // Output: 5 (2, 1, 2)
    }
}