/**
 * PROBLEM: Convert Sorted List to Binary Search Tree
 * * Given the head of a singly linked list where elements are sorted in ascending order, 
 * convert it to a height-balanced Binary Search Tree (BST).
 * * Strategy: Midpoint Tree Splitting
 * Use a fast and slow pointer approach to find the midpoint of the current list segment. 
 * Disconnect the list right before the middle node to split it into two independent sub-lists. 
 * The middle node becomes the tree root. Recursively repeat this process on the left and right halves 
 * to build a balanced tree structure.
 * * Complexity:
 * Time Complexity: $O(N \log N)$
 * Space Complexity: $O(\log N)$ recursion stack frames.
 */
public class SortedListToBST {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        // Find the midpoint of the list and track the node right before it
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect the first half of the list from the middle node
        if (prev != null) {
            prev.next = null;
        }

        TreeNode root = new TreeNode(slow.val);
        
        // Recursively build the left and right subtrees
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);

        return root;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-10); head.next = new ListNode(-3);
        head.next.next = new ListNode(0); head.next.next.next = new ListNode(5); // -10, -3, 0, 5

        TreeNode root = sortedListToBST(head);
        System.out.println("Balanced BST Root item: " + root.val); // 0 or -3 depending on exact floor split choice
    }
}