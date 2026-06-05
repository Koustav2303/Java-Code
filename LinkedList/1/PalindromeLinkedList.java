/**
 * PROBLEM: Palindrome Linked List
 * * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * * Strategy: Split, Reverse, and Compare
 * 1. Find the middle of the list using fast/slow pointers.
 * 2. Reverse the second half of the list in-place.
 * 3. Compare values step-by-step from the head and the reversed midpoint.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class PalindromeLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // Step 1: Locate midpoint
        ListNode slow = head; ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
        }

        // Step 2: Reverse second half context
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Step 3: Mirror check structural symmetry bounds
        ListNode p1 = head;
        ListNode p2 = prev; // Head of the reversed second half
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next; p2 = p2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(2); head.next.next.next = new ListNode(1);

        System.out.println("Is list symmetrical palindrome? " + isPalindrome(head)); // true
    }
}