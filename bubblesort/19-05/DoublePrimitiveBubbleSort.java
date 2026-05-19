import java.util.Arrays;

public class DoublePrimitiveBubbleSort {
    public static void sort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        double[] measurements = {3.14, 1.59, 2.65, 3.58, 9.79};
        System.out.println("Original array: " + Arrays.toString(measurements));
        sort(measurements);
        System.out.println("Sorted array:   " + Arrays.toString(measurements));
    }
}