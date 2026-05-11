interface MathOp { int operate(int a, int b); }

public class LambdaPro {
    public static void main(String[] args) {
        MathOp add = (a, b) -> a + b;
        System.out.println("Sum: " + add.operate(10, 20));
    }
}