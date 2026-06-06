import java.util.Random;

/**
 * PROBLEM: Linked List Random Node
 * * Given a singly linked list, return a random node's value from the list. 
 * Each node must have the exact same probability of being chosen.
 * Solve this using O(1) extra space (streaming/reservoir sampling approach).
 * * Strategy: Reservoir Probability Assignment
 * Traverse the list sequentially. When at the i-th node, pick a random number between 0 and i. 
 * If that random value equals 0, update the sampled result with the current node's value. 
 * This ensures every item maintains a uniform $1/N$ selection chance without pre-calculating the list length.
 */
public class LinkedListRandomNode {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private final ListNode head;
    private final Random rand = new Random();

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }
    
    public int getRandom() {
        ListNode curr = head;
        int scopeSum = 1;
        int result = curr.val;

        while (curr != null) {
            // Pick a random number in range [0, scopeSum - 1]
            if (rand.nextInt(scopeSum) == 0) {
                result = curr.val; // Overwrite current sample selection
            }
            scopeSum++;
            curr = curr.next;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(10); head.next = new ListNode(20); head.next.next = new ListNode(30);
        LinkedListRandomNode sampler = new LinkedListRandomNode(head);
        System.out.println("Random sample draw: " + sampler.getRandom());
    }
}