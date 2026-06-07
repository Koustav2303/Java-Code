import java.util.Stack;

/**
 * PROBLEM: Clumsy Factorial
 * * Given an integer n, calculate its clumsy factorial by applying a fixed cyclic sequence of operators 
 * sequentially to integers in decreasing order: multiplication '*', division '/', addition '+', and subtraction '-'.
 * * Example: clumsy(4) = 4 * 3 / 2 + 1 = 7.
 * * Strategy: Stack Precedence Sieve
 * Use a stack to track intermediate arithmetic terms. Process the elements using a cyclic counter. 
 * Multiplication and division are executed immediately by popping, modifying, and pushing terms back to the stack. 
 * Subtraction is pushed as a negative integer. Sum the stack elements at the end.
 */
public class ClumsyFactorial {
    public static int clumsy(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(n);
        n--;

        int operatorIndex = 0; // 0: '*', 1: '/', 2: '+', 3: '-'
        while (n > 0) {
            if (operatorIndex == 0) {
                stack.push(stack.pop() * n);
            } else if (operatorIndex == 1) {
                stack.push(stack.pop() / n);
            } else if (operatorIndex == 2) {
                stack.push(n);
            } else {
                stack.push(-n); // Represent subtraction as a negative operand
            }
            operatorIndex = (operatorIndex + 1) % 4;
            n--;
        }

        int totalSum = 0;
        for (int val : stack) {
            totalSum += val;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        System.out.println("Clumsy Factorial of 4: " + clumsy(4));   // 7
        System.out.println("Clumsy Factorial of 10: " + clumsy(10)); // 12 (10*9/8 + 7 - 6*5/4 + 3 - 2*1)
    }
}