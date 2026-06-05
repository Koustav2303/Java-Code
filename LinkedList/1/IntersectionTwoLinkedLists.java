/**
 * PROBLEM: Intersection of Two Linked Lists
 * * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. 
 * If the two linked lists have no intersection at all, return null.
 * * Strategy: Traversal Boundary Syncing
 * Use two pointers. Traverse down each list. When a pointer hits null, redirect it to the head of the *other* list. 
 * This path swap balances out any length differences, causing both pointers to meet exactly at the intersection point.
 * * Complexity:
 * Time Complexity: O(N + M)
 * Space Complexity: O(1)
 */
public class IntersectionTwoLinkedLists {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA; // Meets either at the intersection node or null
    }

    public static void main(String[] args) {
        ListNode common = new ListNode(8);
        ListNode a = new ListNode(4); a.next = common;
        ListNode b = new ListNode(5); b.next = common;

        ListNode intersect = getIntersectionNode(a, b);
        System.out.println("Intersection node found at value: " + (intersect != null ? intersect.val : "null")); // 8
    }
}