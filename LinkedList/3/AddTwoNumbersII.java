import java.util.Stack;

/**
 * PROBLEM: Add Two Numbers II
 * * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * You may not modify the input lists (i.e., you cannot reverse them).
 * * Strategy: Dual LIFO Digit Parsing
 * Since we cannot reverse the input lists directly, process them into two Stacks to force 
 * a right-to-left evaluation order. Pop digits, calculate sums with a carry, and construct 
 * the output list backwards by inserting new nodes directly at the head.
 * * Complexity:
 * Time Complexity: O(N + M)
 * Space Complexity: O(N + M) to hold stack states.
 */
public class AddTwoNumbersII {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) { s1.push(l1.val); l1 = l1.next; }
        while (l2 != null) { s2.push(l2.val); l2 = l2.next; }

        ListNode head = null;
        int carry = 0;

        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int sum = carry;
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();

            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            
            // Link node to the front of the tracking head anchor (reverse construction)
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7); l1.next = new ListNode(2); l1.next.next = new ListNode(4); // 724
        ListNode l2 = new ListNode(5); l2.next = new ListNode(6); // 56
        
        ListNode res = addTwoNumbers(l1, l2);
        System.out.print("Sum outcome list: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 7 8 0 (780)
        System.out.println();
    }
}