import java.util.ArrayList;
import java.util.Arrays;

public class RemoveEvenNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        
        System.out.println("Original ArrayList: " + numbers);
        
        // Safely removes any element that matches the condition
        numbers.removeIf(n -> n % 2 == 0);
        
        System.out.println("After removing evens: " + numbers);
    }
}