public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if current spot is empty
            if (flowerbed[i] == 0) {
                // Check left and right boundaries (treat edges as empty '0')
                boolean emptyLeft = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRight = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                
                if (emptyLeft && emptyRight) {
                    flowerbed[i] = 1; // Greedily plant a flower
                    count++;
                }
            }
        }
        
        return count >= n;
    }

    public static void main(String[] args) {
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;
        System.out.println("Can plant " + n + " flowers? " + canPlaceFlowers(flowerbed, n));
    }
}