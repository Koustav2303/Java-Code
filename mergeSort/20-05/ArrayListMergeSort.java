import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListMergeSort {
    public static void merge(List<Integer> list, int left, int mid, int right) {
        List<Integer> L = new ArrayList<>(list.subList(left, mid + 1));
        List<Integer> R = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < L.size() && j < R.size()) {
            if (L.get(i) <= R.get(j)) {
                list.set(k++, L.get(i++));
            } else {
                list.set(k++, R.get(j++));
            }
        }
        while (i < L.size()) list.set(k++, L.get(i++));
        while (j < R.size()) list.set(k++, R.get(j++));
    }

    public static void sort(List<Integer> list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(list, left, mid);
            sort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(99, 44, 22, 11, 66, 33));
        System.out.println("Original list: " + list);
        sort(list, 0, list.size() - 1);
        System.out.println("Sorted list:   " + list);
    }
}