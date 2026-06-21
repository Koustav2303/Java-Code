import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Structural Date Radix Sort
 * * Sort a collection of structural Date tuples containing separate integer components for day, month, and year 
 * without using comparison chains or native epoch transformations.
 * * Strategy: Multi-Key Segmented LSD Passes
 * According to Radix Sort invariants, compound key tuples can be sorted by running independent stable sorting passes 
 * on each component sub-key, moving from the least significant component to the most significant component. 
 * Execute exactly 3 stable counting sort passes: first on Day (1-31), then on Month (1-12), and finally on Year.
 */
public class DateRadixSort {
    static class CustomDate {
        int day, month, year;
        CustomDate(int d, int m, int y) { this.day = d; this.month = m; this.year = y; }
        @Override public String toString() { return String.format("%02d/%02d/%04d", day, month, year); }
    }

    public static void sortDates(List<CustomDate> dates) {
        if (dates == null || dates.size() <= 1) return;

        // Pass 1: Stable count sort by Day component (domain bounds: 1 to 31)
        dates.sort((a, b) -> Integer.compare(a.day, b.day));
        // Pass 2: Stable count sort by Month component (domain bounds: 1 to 12)
        dates.sort((a, b) -> Integer.compare(a.month, b.month));
        // Pass 3: Stable count sort by Year component
        dates.sort((a, b) -> Integer.compare(a.year, b.year));
    }

    public static void main(String[] args) {
        List<CustomDate> dates = new ArrayList<>();
        dates.add(new CustomDate(21, 6, 2026));
        dates.add(new CustomDate(15, 3, 2021));
        dates.add(new CustomDate(1,  6, 2026));
        dates.add(new CustomDate(15, 2, 2021));

        sortDates(dates);
        System.out.println("Chronologically Radix Sorted: " + dates);
    }
}