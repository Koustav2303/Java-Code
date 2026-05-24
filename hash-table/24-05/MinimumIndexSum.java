import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MinimumIndexSum {
    public static String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        List<String> result = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < list2.length; i++) {
            String restaurant = list2[i];
            if (map.containsKey(restaurant)) {
                int indexSum = i + map.get(restaurant);
                
                if (indexSum < minSum) {
                    result.clear();
                    result.add(restaurant);
                    minSum = indexSum;
                } else if (indexSum == minSum) {
                    result.add(restaurant);
                }
            }
        }
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        
        System.out.println("Common favorite with lowest index: " + 
                           Arrays.toString(findRestaurant(list1, list2)));
    }
}