import java.util.Arrays;

public class ObjectMergeSort {
    static class Employee {
        String name;
        int salary;

        Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + "($" + salary + ")";
        }
    }

    public static void merge(Employee[] arr, int left, int mid, int right) {
        Employee[] L = Arrays.copyOfRange(arr, left, mid + 1);
        Employee[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i].salary <= R[j].salary) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(Employee[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Alice", 70000),
            new Employee("Bob", 50000),
            new Employee("Charlie", 90000)
        };
        System.out.println("Original array: " + Arrays.toString(staff));
        sort(staff, 0, staff.length - 1);
        System.out.println("Sorted by salary: " + Arrays.toString(staff));
    }
}