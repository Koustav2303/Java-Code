import java.util.HashSet;

public class DuplicateFinder {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 4, 2};
        find(nums);
    }

    public static void find(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        System.out.print("Duplicates: ");
        for (int i : arr) {
            if (!set.add(i)) System.out.print(i + " ");
        }
    }
}