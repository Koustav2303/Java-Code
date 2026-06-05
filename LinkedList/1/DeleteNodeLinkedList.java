/**
 * PROBLEM: Delete Node in a Linked List
 * * You are given access ONLY to the node that is to be deleted directly. You do not have access to the head.
 * * Strategy: Value Overwriting Shift
 * Since you cannot access the previous node to change its next reference, copy the data 
 * of the *next* node into the current node, then delete that next node instead.
 * * Complexity:
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class DeleteNodeLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void deleteNode(ListNode node) {
        // Overwrite current node data with adjacent neighbor parameters
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode target = new ListNode(5);
        head.next = target; target.next = new ListNode(9);

        deleteNode(target);
        System.out.print("List after deletion: ");
        while (head != null) { System.out.print(head.val + " "); head = head.next; } // 4 9
        System.out.println();
    }
}