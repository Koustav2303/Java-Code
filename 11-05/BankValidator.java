class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) { super(msg); }
}

public class BankValidator {
    public static void main(String[] args) {
        int balance = 500, withdrawal = 1000;
        try {
            if (withdrawal > balance) throw new InsufficientFundsException("Balance too low!");
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}