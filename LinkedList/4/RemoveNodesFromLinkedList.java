/**
 * PROBLEM: Remove Nodes From Linked List
 * * Remove every node from the linked list that has a node with a strictly greater value 
 * anywhere to its right side.
 * * Strategy: Right-to-Left Recursive Pruning
 * Resolving this from left to right is tricky because a large value far to the right can cause cascading removals. 
 * Instead, process the list from right to left using recursion. 
 * The recursive call unwinds from the tail, returning the maximum valid head node found so far. 
 * If the current node's value is smaller than its recursively processed neighbor's value, 
 * prune the current node and return the neighbor instead.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N) recursion depth footprint.
 */
public class RemoveNodesFromLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode removeNodes(ListNode head) {
        if (head == null || head.next == null) return head;

        // Recurse down to the tail node to establish our base right-side baseline condition
        ListNode nextNode = removeNodes(head.next);

        // If the right neighbor contains a larger value, prune the current node from the sequence
        if (head.val < nextNode.val) {
            return nextNode;
        }

        head.next = nextNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5); head.next = new ListNode(2);
        head.next.next = new ListNode(13); head.next.next.next = new ListNode(3); head.next.next.next.next = new ListNode(8);

        ListNode res = removeNodes(head); // 5 and 2 are removed because of 13. 3 is removed because of 8.
        System.out.print("Monotonic Monotonic Filter Outcome: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 13 8
        System.out.println();
    }
}