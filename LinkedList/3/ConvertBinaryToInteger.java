/**
 * PROBLEM: Convert Binary Number in a Linked List to Integer
 * * Given head which is a reference node to a singly linked list where the value of each node 
 * is either 0 or 1. The linked list holds the binary representation of a number.
 * Return the decimal value of the number in the linked list.
 * * Strategy: Left-Shift Cumulative Matrix
 * Traverse the list from head to tail. For each node, shift your running total left by 1 bit position 
 * (equivalent to multiplying by 2) and perform a bitwise OR operation with the current node's value.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class ConvertBinaryToInteger {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int getDecimalValue(ListNode head) {
        int decimalValue = 0;
        while (head != null) {
            // Shift current running total left and insert the new binary digit bit value
            decimalValue = (decimalValue << 1) | head.val;
            head = head.next;
        }
        return decimalValue;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(0); head.next.next = new ListNode(1); // 101 binary

        System.out.println("Converted Decimal Output: " + getDecimalValue(head)); // 5
    }
}