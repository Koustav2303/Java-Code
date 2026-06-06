/**
 * PROBLEM: Maximum Twin Sum of a Linked List
 * * In a linked list of even size n, the ith node (0-indexed) is the twin of the (n - 1 - i)th node.
 * The twin sum is defined as the sum of a node and its twin. 
 * Return the maximum twin sum of the linked list.
 * * Strategy: Midpoint Reversal Convergence
 * Locate the midpoint of the even list using fast/slow pointers. Split the list in half 
 * and reverse the second half in-place. Run a parallel step loop from both heads simultaneously, 
 * adding symmetric pairs and tracking the maximum sum encountered.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class TwinSumLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int pairSum(ListNode head) {
        // Step 1: Find the midpoint of the list using fast/slow pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list in-place
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Step 3: Iterate through both halves simultaneously to calculate maximum twin sums
        int maxTwinSum = 0;
        ListNode firstHalfPointer = head;
        ListNode secondHalfPointer = prev; // Head of the reversed second half
        
        while (secondHalfPointer != null) {
            maxTwinSum = Math.max(maxTwinSum, firstHalfPointer.val + secondHalfPointer.val);
            firstHalfPointer = firstHalfPointer.next;
            secondHalfPointer = secondHalfPointer.next;
        }

        return maxTwinSum;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5); head.next = new ListNode(4);
        head.next.next = new ListNode(2); head.next.next.next = new ListNode(1); //Twins: (5,1)=6, (4,2)=6

        System.out.println("Maximum Twin Sum: " + pairSum(head)); // 6
    }
}