public class PrimeValidator {
    public static void main(String[] args) {
        int n = 29;
        boolean isPrime = n > 1;
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n % i == 0) { isPrime = false; break; }
        }
        System.out.println(n + " is prime: " + isPrime);
    }
}