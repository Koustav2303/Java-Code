import java.util.Arrays;
import java.util.Comparator;

public class ComparatorBubbleSort {
    public static void sort(String[] arr, Comparator<String> comparator) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Using the custom comparator instead of default > or <
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"Elephant", "Cat", "Hippopotamus", "Dog", "Fish"};
        System.out.println("Original array: " + Arrays.toString(words));
        
        // Sorting by length using a lambda expression
        sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        
        System.out.println("Sorted by length: " + Arrays.toString(words));
    }
}