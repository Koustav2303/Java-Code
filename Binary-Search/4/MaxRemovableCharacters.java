public class MaxRemovableCharacters {
    public static int maximumRemovals(String s, String p, int[] removable) {
        int low = 0, high = removable.length;
        int best = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (isSubsequence(s, p, removable, mid)) {
                best = mid; // Subsequence still valid, try removing more
                low = mid + 1;
            } else {
                high = mid - 1; // Removed too much
            }
        }
        return best;
    }
    
    private static boolean isSubsequence(String s, String p, int[] removable, int k) {
        char[] sChars = s.toCharArray();
        // Mask out the first 'k' removable characters
        for (int i = 0; i < k; i++) {
            sChars[removable[i]] = '*'; // Mark as removed
        }
        
        int pIndex = 0;
        for (int i = 0; i < sChars.length && pIndex < p.length(); i++) {
            if (sChars[i] == p.charAt(pIndex)) {
                pIndex++;
            }
        }
        return pIndex == p.length();
    }

    public static void main(String[] args) {
        String s = "abcacb", p = "ab";
        int[] removable = {3, 1, 0};
        System.out.println("Max removable characters: " + maximumRemovals(s, p, removable)); // 2
    }
}