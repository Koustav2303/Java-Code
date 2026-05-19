import java.util.Arrays;

public class RecordBubbleSort {
    // A modern Java 'record' instead of a traditional class
    record Student(String name, double grade) {}

    public static void sort(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Sorting by grade, highest to lowest
                if (students[j].grade() < students[j + 1].grade()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Student[] classList = {
            new Student("David", 85.5),
            new Student("Sarah", 92.0),
            new Student("John", 78.5)
        };
        
        System.out.println("Original array: " + Arrays.toString(classList));
        sort(classList);
        System.out.println("Sorted by grade (descending): " + Arrays.toString(classList));
    }
}