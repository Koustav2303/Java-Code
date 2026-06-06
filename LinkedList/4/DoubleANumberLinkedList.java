/**
 * PROBLEM: Double a Number Represented as a Linked List
 * * Given the head of a non-empty linked list representing a non-negative integer, 
 * double its value in-place and return the head of the modified list.
 * * Strategy: Look-Ahead Carry Injection
 * We can solve this in a single forward pass without reversing the list. For each node, if its next 
 * neighbor's value is greater than 4, it will generate a carry of 1. Double the current value, 
 * add the look-ahead carry, and keep only the single digit: `(curr.val * 2) % 10`. 
 * If the original head node's value is greater than 4, dynamically instantiate a new head node to catch the overflow.
 */
public class DoubleANumberLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode doubleIt(ListNode head) {
        // If head value creates an absolute overflow carry, inject a preceding node layer
        if (head.val > 4) {
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            head = newHead;
        }

        ListNode curr = head;
        while (curr != null) {
            curr.val = (curr.val * 2) % 10;
            // Inject carry look-ahead check if next neighbor threshold overflows
            if (curr.next != null && curr.next.val > 4) {
                curr.val += 1;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(8); head.next.next = new ListNode(9); // 189

        ListNode res = doubleIt(head); // 189 * 2 = 378
        System.out.print("Doubled List: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 3 7 8
        System.out.println();
    }
}