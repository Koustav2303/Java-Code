/**
 * PROBLEM: Intersection of Two Sorted Linked Lists
 * * Given the heads of two sorted linked lists, construct a new linked list representing 
 * the exact intersection (common element pairs) of both lists.
 * * Strategy: Parallel Value Comparison Sweep
 * Since both lists are pre-sorted, pass two independent pointers simultaneously down the list structures. 
 * If values match, create an intersection output node and advance both pointers. 
 * If values differ, increment only the pointer referencing the smaller current value to preserve match opportunities.
 */
public class IntersectionSortedLists {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode findIntersection(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val == head2.val) {
                curr.next = new ListNode(head1.val);
                curr = curr.next;
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.val < head2.val) {
                head1 = head1.next; // Catch up pointer 1 magnitude
            } else {
                head2 = head2.next; // Catch up pointer 2 magnitude
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1); l1.next = new ListNode(2); l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(2); l2.next = new ListNode(4); l2.next.next = new ListNode(6);

        ListNode res = findIntersection(l1, l2); // Intersection elements: 2, 4
        System.out.print("Intersection matching entries: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}