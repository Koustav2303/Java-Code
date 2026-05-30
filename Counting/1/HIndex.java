/**
 * PROBLEM: H-Index
 * * Given an array of integers citations where citations[i] is the number of citations a researcher received 
 * for their ith paper, return the researcher's h-index.
 * The h-index is defined as the maximum value of h such that the given researcher has published 
 * at least h papers that have each been cited at least h times.
 * * Example:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * * Approach:
 * Instead of sorting (O(N log N)), we use Counting Sort arrays in O(N) time.
 * We count the frequencies of citations. Any citation count greater than the total number of papers (N) 
 * is just capped at N. We then iterate backward to find the H-Index.
 */
public class HIndex {
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        
        // Count frequencies, capping at n
        for (int c : citations) {
            if (c >= n) {
                count[n]++;
            } else {
                count[c]++;
            }
        }
        
        // Iterate backward to calculate cumulative papers and find the threshold
        int cumulativePapers = 0;
        for (int i = n; i >= 0; i--) {
            cumulativePapers += count[i];
            // If the number of papers with at least 'i' citations is >= 'i', that's our H-Index
            if (cumulativePapers >= i) {
                return i;
            }
        }
        
        return 0;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println("H-Index: " + hIndex(citations)); // 3
    }
}