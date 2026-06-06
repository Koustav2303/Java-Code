/**
 * PROBLEM: Merge In Between Linked Lists
 * * You are given two linked lists, list1 and list2 of sizes n and m respectively. Remove list1's nodes 
 * from the ath node to the bth node, and put list2 in their place.
 * * Strategy: Pointer Offset Splice
 * Advance a tracking pointer `start` to index `a - 1`, and a second pointer `end` to index `b + 1`. 
 * Connect `start.next` to the head of `list2`. Traverse to the tail of `list2`, and connect it to `end`.
 * * Complexity:
 * Time Complexity: O(N + M)
 * Space Complexity: O(1)
 */
public class MergeInBetweenLinkedLists {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode start = list1;
        for (int i = 1; i < a; i++) {
            start = start.next;
        }

        ListNode end = start;
        for (int i = a; i <= b + 1; i++) {
            end = end.next;
        }

        start.next = list2; // Splice list2 head in place
        
        ListNode tail2 = list2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }
        
        tail2.next = end; // Connect list2 tail back to list1 remainder
        return list1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0); l1.next = new ListNode(1);
        l1.next.next = new ListNode(2); l1.next.next.next = new ListNode(3); // list1: 0->1->2->3
        ListNode l2 = new ListNode(10); // list2: 10

        ListNode res = mergeInBetween(l1, 1, 2, l2); // Remove elements from index 1 to 2 (1,2) -> 0->10->3
        System.out.print("Spliced Output List: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; }
        System.out.println();
    }
}