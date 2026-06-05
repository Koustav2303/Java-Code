/**
 * PROBLEM: Merge Two Sorted Lists
 * * Merge two sorted linked lists and return it as a sorted list. The list should be made by 
 * splicing together the nodes of the first two lists.
 * * Strategy: Dummy Sentinel Anchor
 * Maintain a dummy node to hold the head reference. Iterate through both lists, 
 * appending the smaller of the two current nodes to your merged list at each step.
 * * Complexity:
 * Time Complexity: O(N + M)
 * Space Complexity: O(1)
 */
public class MergeTwoSortedLists {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // Attach any remaining leftover nodes from either list
        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1); l1.next = new ListNode(2); l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1); l2.next = new ListNode(3); l2.next.next = new ListNode(4);

        ListNode res = mergeTwoLists(l1, l2);
        System.out.print("Merged sorted values: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 1 2 3 4 4
        System.out.println();
    }
}