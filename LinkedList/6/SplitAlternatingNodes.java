/**
 * PROBLEM: Split Alternating Nodes from Linked List
 * * Given a singly linked list, split it into two independent linked lists such that list A contains 
 * all odd position index nodes (1st, 3rd, 5th...) and list B contains all even position nodes (2nd, 4th, 6th...).
 * * Strategy: Interleaved Step Pointer Splicing
 * Maintain tracking pointers for both independent chains. Step forward two nodes at a time, routing alternate 
 * next pointers into their respective destinations. Make sure to terminate both chains with null pointers 
 * at the end to prevent cycles.
 */
public class SplitAlternatingNodes {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode[] splitAlternating(ListNode head) {
        if (head == null) return new ListNode[]{null, null};

        ListNode headA = head;
        ListNode headB = head.next;

        ListNode currA = headA;
        ListNode currB = headB;

        while (currA != null && currB != null && currB.next != null) {
            currA.next = currB.next;
            currA = currA.next;
            
            currB.next = currA.next;
            currB = currB.next;
        }

        if (currA != null) currA.next = null; // Terminate list A cleanly
        if (currB != null) currB.next = null; // Terminate list B cleanly

        return new ListNode[]{headA, headB};
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4); // 1->2->3->4

        ListNode[] parts = splitAlternating(head); // List A: 1->3, List B: 2->4
        System.out.print("Odd position index chain: ");
        ListNode a = parts[0]; while (a != null) { System.out.print(a.val + " "); a = a.next; }
        System.out.println();
    }
}