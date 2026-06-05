/**
 * PROBLEM: Partition List
 * * Given the head of a linked list and a value x, partition it such that all nodes less than x 
 * come before nodes greater than or equal to x. Preserve the original relative order of nodes in each partition.
 * * Strategy: Split and Connect
 * Maintain two separate chains using dummy nodes: `less` and `greaterOrEqual`. 
 * Traverse the original list, routing nodes into their respective chains based on their value relative to $x$. 
 * Connect the two chains at the end.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class PartitionList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null; // Cut off any potential cycles
        before.next = afterHead.next; // Connect the two partitioned lists
        
        return beforeHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1); head.next = new ListNode(4);
        head.next.next = new ListNode(3); head.next.next.next = new ListNode(25);

        ListNode res = partition(head, 3);
        System.out.print("Partitioned List around 3: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 25 4 3
        System.out.println();
    }
}