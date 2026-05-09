public class DigitCounter {
    public static void main(String[] args) {
        int num = 12345, count = 0;
        while(num != 0) {
            num /= 10;
            count++;
        }
        System.out.println("Total Digits: " + count);
    }
}