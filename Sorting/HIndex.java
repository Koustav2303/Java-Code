import java.util.Arrays;

public class HIndex {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        for (int i = 0; i < n; i++) {
            // How many papers have at least citations[i] citations?
            int papersWithAtLeastThisMany = n - i;
            
            // If the number of citations is >= the number of papers
            if (citations[i] >= papersWithAtLeastThisMany) {
                return papersWithAtLeastThisMany;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println("Citations: " + Arrays.toString(citations));
        System.out.println("H-Index: " + hIndex(citations)); // Output: 3
    }
}