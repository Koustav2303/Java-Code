/**
 * PROBLEM: Insert Greatest Common Divisors in Linked List
 * * Given the head of a linked list where every node contains an integer value, insert a new node 
 * between every pair of adjacent nodes with a value equal to the Greatest Common Divisor (GCD) of those two nodes.
 * * Strategy: Pairwise Euclidean Injection
 * Traverse the list using a single pointer. For each pair of adjacent nodes (`curr` and `curr.next`), 
 * calculate their GCD using the Euclidean algorithm. Instantiate a new node with this value 
 * and splice it directly between them before advancing the pointer.
 */
public class InsertGreatestCommonDivisors {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode curr = head;
        while (curr.next != null) {
            int gcdValue = gcd(curr.val, curr.next.val);
            ListNode middleNode = new ListNode(gcdValue);
            
            // Interleave the middle calculation node into the sequence chain
            middleNode.next = curr.next;
            curr.next = middleNode;
            
            curr = middleNode.next; // Advance past the newly spliced node
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(18); head.next = new ListNode(6); head.next.next = new ListNode(10);

        ListNode res = insertGreatestCommonDivisors(head); // GCD(18,6)=6, GCD(6,10)=2
        System.out.print("List with GCD Slices: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 18 6 6 2 10
        System.out.println();
    }
}