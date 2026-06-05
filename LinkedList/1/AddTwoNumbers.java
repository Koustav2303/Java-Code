/**
 * PROBLEM: Add Two Numbers
 * * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * * Strategy: Dummy Node Carry Tracking
 * Traverse both lists simultaneously. Maintain a running 'carry' variable. 
 * Generate new nodes by calculating (val1 + val2 + carry) % 10.
 * * Complexity:
 * Time Complexity: O(Max(N, M))
 * Space Complexity: O(Max(N, M)) for the output list.
 */
public class AddTwoNumbers {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2); l1.next = new ListNode(4); l1.next.next = new ListNode(3); // 342
        ListNode l2 = new ListNode(5); l2.next = new ListNode(6); l2.next.next = new ListNode(4); // 465
        
        ListNode res = addTwoNumbers(l1, l2);
        System.out.print("Sum List: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 7 -> 0 -> 8 (807)
        System.out.println();
    }
}