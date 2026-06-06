public class ValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        
        long low = 1;
        long high = num / 2;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int valid = 16;
        int invalid = 14;
        
        System.out.println("Is " + valid + " a perfect square? " + isPerfectSquare(valid));
        System.out.println("Is " + invalid + " a perfect square? " + isPerfectSquare(invalid));
    }
}