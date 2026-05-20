import java.util.Arrays;

public class ObjectQuickSort {
    static class Product {
        String name;
        double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + "($" + price + ")";
        }
    }

    public static int partition(Product[] arr, int low, int high) {
        double pivot = arr[high].price;
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // Sort by price DESCENDING
            if (arr[j].price >= pivot) {
                i++;
                Product temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Product temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void sort(Product[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Product[] inventory = {
            new Product("Laptop", 999.99),
            new Product("Mouse", 25.50),
            new Product("Monitor", 150.00),
            new Product("Keyboard", 45.99)
        };
        
        System.out.println("Original array: " + Arrays.toString(inventory));
        sort(inventory, 0, inventory.length - 1);
        System.out.println("Sorted by price (desc): " + Arrays.toString(inventory));
    }
}