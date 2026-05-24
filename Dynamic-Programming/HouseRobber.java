import java.util.Arrays;

public class HouseRobber {
    public static int rob(int[] nums) {
        int rob1 = 0, rob2 = 0;
        // [rob1, rob2, n, n+1, ...]
        for (int n : nums) {
            int temp = Math.max(n + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    public static void main(String[] args) {
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("Houses: " + Arrays.toString(houses));
        System.out.println("Max loot: " + rob(houses));
    }
}