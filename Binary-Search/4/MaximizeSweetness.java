public class MaximizeSweetness {
    public static int maximizeSweetness(int[] sweetness, int k) {
        int low = 1, high = 0;
        for (int s : sweetness) high += s;
        
        int best = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canDivide(sweetness, k + 1, mid)) {
                best = mid; // This minimum sweetness works, try for higher
                low = mid + 1;
            } else {
                high = mid - 1; // Expected sweetness is too high to make K+1 pieces
            }
        }
        return best;
    }
    
    private static boolean canDivide(int[] sweetness, int piecesNeeded, int targetMinSweetness) {
        int chunks = 0;
        int currentSweetness = 0;
        
        for (int s : sweetness) {
            currentSweetness += s;
            if (currentSweetness >= targetMinSweetness) {
                chunks++;
                currentSweetness = 0; // Reset for the next chunk
            }
        }
        return chunks >= piecesNeeded;
    }

    public static void main(String[] args) {
        int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 5; // 6 pieces total
        System.out.println("Maximum sweetness you can guarantee: " + maximizeSweetness(sweetness, k)); // 6
    }
}