/**
 * PROBLEM: Lemonade Change
 * * At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you 
 * and order one at a time. Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.
 * You must provide the correct change to each customer so that the net transaction is that the customer pays $5.
 * Return true if and only if you can provide every customer with the correct change.
 * * Approach:
 * Simulate the cash register. Greedily give back $10 bills as change before $5 bills, 
 * because $5 bills are more versatile.
 */
public class LemonadeChange {
    public static boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        
        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                if (fives == 0) return false;
                fives--;
                tens++;
            } else { // bill == 20
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println("Can provide change? " + lemonadeChange(bills)); // true
    }
}