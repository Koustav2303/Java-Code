import java.util.Arrays;

public class ObjectBubbleSort {
    static class Person {
        String name;
        int age;
        Person(String name, int age) { this.name = name; this.age = age; }
        
        // Tells Java how to print this object
        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void sort(Person[] people) {
        int n = people.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (people[j].age > people[j + 1].age) { // Sorting by age
                    Person temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Person[] people = {
            new Person("Alice", 30),
            new Person("Bob", 20),
            new Person("Charlie", 25)
        };
        
        System.out.println("Original array: " + Arrays.toString(people));
        sort(people);
        System.out.println("Sorted array:   " + Arrays.toString(people));
    }
}