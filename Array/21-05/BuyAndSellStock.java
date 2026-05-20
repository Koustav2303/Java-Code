import java.util.Arrays;

public class BuyAndSellStock {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            // Keep track of the lowest price we've seen so far
            if (price < minPrice) {
                minPrice = price;
            } 
            // See if selling today yields a better profit than our current max
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] stockPrices = {7, 1, 5, 3, 6, 4};
        System.out.println("Stock prices: " + Arrays.toString(stockPrices));
        System.out.println("Maximum Profit: " + maxProfit(stockPrices)); // Should be 5 (buy at 1, sell at 6)
    }
}