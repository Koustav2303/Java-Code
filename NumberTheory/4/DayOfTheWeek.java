/**
 * PROBLEM: Day of the Week
 * * Given three integers representing the day, month, and year, return the corresponding day of the week 
 * as a string (e.g., "Sunday", "Monday"). Do not use any built-in date/time libraries.
 * * Strategy: Zeller's Congruence Formula
 * Apply Zeller's mathematical congruence formula:
 * $$h = \left( q + \lfloor\frac{13(m+1)}{5}\rfloor + K + \lfloor\frac{K}{4}\rfloor + \lfloor\frac{J}{4}\rfloor - 2J \right) \pmod 7$$
 * Where $q$ is the day, $m$ is the modified month, $K$ is the year of the century, and $J$ is the zero-based century. 
 * Note: January and February must be treated as months 13 and 14 of the *previous year*.
 */
public class DayOfTheWeek {
    public static String dayOfTheWeek(int day, int month, int year) {
        // Adjust January and February to months 13 and 14 of the previous year
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }

        int q = day;
        int m = month;
        int k = year % 100; // Year of the century
        int j = year / 100; // Zero-based century

        int h = (q + (13 * (m + 1)) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
        
        // Handle negative remainders in modular arithmetic safely
        if (h < 0) h = (h + 7) % 7;

        // Zeller's output mapping: 0 = Saturday, 1 = Sunday, 2 = Monday, ...
        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        return days[h];
    }

    public static void main(String[] args) {
        // May 22, 2026 was a Friday (matching the timestamps of your git workflow setup!)
        System.out.println("Day for 22-05-2026: " + dayOfTheWeek(22, 5, 2026)); // Friday
    }
}