import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * PROBLEM: LFU Cache
 * * Design and implement a data structure for a Least Frequently Used (LFU) cache. 
 * Both get and put operations must run in O(1) average time complexity.
 * * Strategy: Dual Map LinkedHashSet Grouping
 * Maintain three state elements: a `vals` map tracking keys to values, a `counts` map tracking keys 
 * to their usage frequencies, and a `lists` map matching frequencies to an ordered LinkedHashSet of keys. 
 * Track `minFrequency` dynamically to find evictions quickly when capacity is reached.
 */
public class LFUCache {
    private final int capacity;
    private int minFrequency;
    private final Map<Integer, Integer> vals = new HashMap<>();
    private final Map<Integer, Integer> counts = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Integer>> lists = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = -1;
    }
    
    public int get(int key) {
        if (!vals.containsKey(key)) return -1;
        int count = counts.get(key);
        counts.put(key, count + 1);
        
        lists.get(count).remove(key);
        if (count == minFrequency && lists.get(count).isEmpty()) {
            minFrequency++;
        }
        lists.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
        return vals.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) return;
        
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key); // Triggers frequency update logic
            return;
        }
        
        if (vals.size() >= capacity) {
            int evict = lists.get(minFrequency).iterator().next(); // Evict least frequently used key
            lists.get(minFrequency).remove(evict);
            vals.remove(evict);
            counts.remove(evict);
        }
        
        vals.put(key, value);
        counts.put(key, 1);
        minFrequency = 1;
        lists.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " "); // 1 (frequency of 1 becomes 2)
        cache.put(3, 3);                      // Evicts key 2 because its frequency is lower
        System.out.println(cache.get(2));     // -1 (not found)
    }
}