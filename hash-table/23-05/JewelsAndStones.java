import java.util.HashSet;

public class JewelsAndStones {
    public static int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> jewelSet = new HashSet<>();
        
        // Add all valid jewels into a HashSet
        for (char j : jewels.toCharArray()) {
            jewelSet.add(j);
        }

        int count = 0;
        // Count how many of your stones exist in the jewel set
        for (char s : stones.toCharArray()) {
            if (jewelSet.contains(s)) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        String jewels = "aA";
        String stones = "aAAbbbb";
        
        System.out.println("Jewel types: " + jewels);
        System.out.println("Your stones: " + stones);
        System.out.println("Total jewels you own: " + numJewelsInStones(jewels, stones));
    }
}