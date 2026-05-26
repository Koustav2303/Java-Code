import java.util.Arrays;

public class AssignCookies {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // Sort greed factors
        Arrays.sort(s); // Sort cookie sizes
        
        int child = 0;
        int cookie = 0;
        
        while (child < g.length && cookie < s.length) {
            // If the cookie is big enough, the child gets it
            if (s[cookie] >= g[child]) {
                child++;
            }
            // Move to the next cookie regardless
            cookie++;
        }
        
        return child; // Number of children who got cookies
    }

    public static void main(String[] args) {
        int[] greed = {1, 2, 3};
        int[] cookies = {1, 1};
        System.out.println("Content children: " + findContentChildren(greed, cookies)); // Output: 1
    }
}