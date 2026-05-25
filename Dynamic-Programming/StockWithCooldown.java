import java.util.Arrays;

public class StockWithCooldown {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        
        int buy = -prices[0];
        int sell = 0;
        int cooldown = 0;
        
        for (int i = 1; i < prices.length; i++) {
            int prevBuy = buy;
            int prevSell = sell;
            
            // We either keep holding, or we buy today (must be from cooldown state)
            buy = Math.max(prevBuy, cooldown - prices[i]);
            
            // We either keep our sold state, or we sell today (must be from holding state)
            sell = Math.max(prevSell, prevBuy + prices[i]);
            
            // Cooldown state is just whatever the sell state was yesterday
            cooldown = prevSell;
        }
        
        return Math.max(sell, cooldown);
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println("Stock Prices: " + Arrays.toString(prices));
        System.out.println("Max Profit (with cooldown): " + maxProfit(prices));
    }
}