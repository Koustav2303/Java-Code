import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: Copy List with Random Pointer
 * * A linked list of length n is given such that each node contains an additional random pointer, 
 * which could point to any node in the list, or null. Construct a deep copy of the list.
 * * Strategy: Map Caching Discovery
 * Use a HashMap to map every original Node to its brand new cloned Node counterpart. 
 * Pass 1 clones nodes; Pass 2 links the respective 'next' and 'random' pointers.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class CopyListWithRandomPointer {
    static class Node {
        int val; Node next; Node random;
        Node(int val) { this.val = val; }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        
        // Pass 1: Clone all nodes independently
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Pass 2: Connect structural pointers
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1); Node n2 = new Node(2);
        n1.next = n2; n1.random = n2; n2.random = n2;

        Node clone = copyRandomList(n1);
        System.out.println("Cloned root node value: " + clone.val);
        System.out.println("Are memory references identical? " + (n1 == clone)); // false
    }
}