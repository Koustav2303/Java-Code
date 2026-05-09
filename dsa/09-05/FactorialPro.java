public class FactorialPro {
    public static void main(String[] args) {
        System.out.println("Factorial of 5: " + fact(5));
    }

    public static int fact(int n) {
        if (n <= 1) return 1;
        return n * fact(n - 1);
    }
}