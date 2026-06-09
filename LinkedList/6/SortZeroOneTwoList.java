/**
 * PROBLEM: Sort a Linked List of 0s, 1s, and 2s
 * * Given a linked list containing only values 0, 1, and 2, sort the linked list in-place 
 * without modifying node values directly (only re-link pointer references).
 * * Strategy: Three Dummy Bucket Sublists
 * Instantiate three standalone sublist dummy nodes (`zeroDummy`, `oneDummy`, `twoDummy`). 
 * Traverse the source list routing nodes into their matching bucket sublists based on their value. 
 * Conclude by linking the tail of the zero sublist to the head of the one sublist, and the tail 
 * of the one sublist to the head of the two sublist.
 */
public class SortZeroOneTwoList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode sortList(ListNode head) {
        ListNode zeroDummy = new ListNode(0); ListNode zero = zeroDummy;
        ListNode oneDummy = new ListNode(0);  ListNode one = oneDummy;
        ListNode twoDummy = new ListNode(0);  ListNode two = twoDummy;

        ListNode curr = head;
        while (curr != null) {
            if (curr.val == 0) {
                zero.next = curr; zero = zero.next;
            } else if (curr.val == 1) {
                one.next = curr; one = one.next;
            } else {
                two.next = curr; two = two.next;
            }
            curr = curr.next;
        }

        // Connect the independent bucket sublists back together seamlessly
        two.next = null; // Sever trailing link pointer to ensure list terminates cleanly
        one.next = twoDummy.next;
        zero.next = oneDummy.next;

        return zeroDummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2); head.next = new ListNode(1); head.next.next = new ListNode(0);

        ListNode res = sortList(head); // Outcome -> 0 1 2
        System.out.print("Sorted 0-1-2 List elements: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}