/**
 * PROBLEM: Odd Even Linked List
 * * Given the head of a singly linked list, group all the nodes with odd indices together followed by 
 * the nodes with even indices, and return the reordered list.
 * * Strategy: Parallel Pointer Splicing
 * Maintain separate chains for odd and even nodes. Traverse the list, linking odd nodes together 
 * and even nodes together. Connect the end of the odd chain to the head of the even chain at the end.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class OddEvenLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // Cache to connect chains at the end

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead; // Connect odd tail to even head
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(2);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(4);

        ListNode res = oddEvenList(head);
        System.out.print("Odd-Even Reordered: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 3 2 4
        System.out.println();
    }
}