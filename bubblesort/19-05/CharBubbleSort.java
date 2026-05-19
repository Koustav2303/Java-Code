import java.util.Arrays;

public class CharBubbleSort {
    public static void sort(char[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] letters = {'z', 'x', 'a', 'm', 'b', 'q'};
        System.out.println("Original characters: " + Arrays.toString(letters));
        sort(letters);
        System.out.println("Sorted characters:   " + Arrays.toString(letters));
    }
}