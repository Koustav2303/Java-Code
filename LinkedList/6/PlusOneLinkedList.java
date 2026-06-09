/**
 * PROBLEM: Plus One Linked List
 * * Given a non-negative integer represented as a non-empty singly linked list of digits, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list.
 * * Strategy: Rightmost Non-Nine Anchor
 * Traverse the entire list finding the rightmost node whose value is strictly less than 9. 
 * Increment that node's value by 1, then convert the values of all subsequent nodes down the remainder chain 
 * to exactly 0. If all nodes contain 9s, instantiate a new node layer at the front to manage the overflow carry.
 */
public class PlusOneLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode notNine = dummy;

        // Locate the absolute rightmost non-nine target candidate item
        ListNode curr = head;
        while (curr != null) {
            if (curr.val != 9) {
                notNine = curr;
            }
            curr = curr.next;
        }

        notNine.val += 1;
        curr = notNine.next;
        
        // Convert all subsequent cascading trail digits to zero elements
        while (curr != null) {
            curr.val = 0;
            curr = curr.next;
        }

        return dummy.val != 0 ? dummy : dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next = new ListNode(9); // 129

        ListNode res = plusOne(head); // 129 + 1 = 130
        System.out.print("Plus One Calculation: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}