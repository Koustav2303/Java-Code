import java.util.HashSet;

public class ArrayIntersection {
    public static void main(String[] args) {
        int[] a = {1, 2, 2, 1}, b = {2, 2};
        HashSet<Integer> set = new HashSet<>();
        for (int i : a) set.add(i);
        System.out.print("Intersection: ");
        for (int i : b) {
            if (set.contains(i)) {
                System.out.print(i + " ");
                set.remove(i);
            }
        }
    }
}