public class PerfectNumber {
    public static boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;
        
        int sum = 1; // 1 is always a divisor
        
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                // Add the corresponding pair divisor (e.g., if checking 28, 2 is a divisor, so add 14)
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        
        return sum == num;
    }

    public static void main(String[] args) {
        int num = 28; // Divisors: 1, 2, 4, 7, 14. Sum = 28.
        System.out.println("Is " + num + " a perfect number? " + checkPerfectNumber(num));
    }
}