import java.time.LocalDate;
import java.util.Arrays;

public class DateBubbleSort {
    public static void sort(LocalDate[] dates) {
        int n = dates.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare if one date is chronologically after the other
                if (dates[j].isAfter(dates[j + 1])) {
                    LocalDate temp = dates[j];
                    dates[j] = dates[j + 1];
                    dates[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        LocalDate[] dates = {
            LocalDate.of(2025, 12, 25), // Christmas 2025
            LocalDate.of(2023, 1, 1),   // New Year 2023
            LocalDate.of(2024, 7, 4)    // Independence Day 2024
        };
        
        System.out.println("Original dates: " + Arrays.toString(dates));
        sort(dates);
        System.out.println("Sorted chronologically: " + Arrays.toString(dates));
    }
}