import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: LRU Cache
 * * Design a data structure for a Least Recently Used (LRU) cache supporting get and put in O(1) time.
 * * Strategy: Custom Doubly Linked List Map Integration
 * Use a HashMap to map keys to nodes in a doubly linked list. 
 * When a key is accessed or modified, move its node to the front of the list. 
 * If capacity is exceeded, evict the node at the tail of the list.
 */
public class LRUCache {
    static class Node {
        int key, val; Node prev, next;
        Node(int k, int v) { this.key = k; this.val = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(-1, -1);
    private final Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insertAtHead(node); // Mark as most recently used
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            remove(node);
            insertAtHead(node);
            return;
        }
        if (map.size() == capacity) {
            map.remove(tail.prev.key); // Remove from tracking map
            remove(tail.prev);        // Evict least recently used node from list
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        insertAtHead(newNode);
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.print(cache.get(1) + " "); // 10
        cache.put(3, 30);                     // Evicts key 2
        System.out.println(cache.get(2));     // -1 (evicted)
    }
}