import java.util.PriorityQueue;

/**
 * PROBLEM: Merge k Sorted Lists
 * * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * * Strategy: PriorityQueue Tracking
 * Initialize a Min-Heap. Add the head node of each non-empty list into the heap. Pop the smallest node, 
 * append it to your merged list, and push that popped node's next element back into the heap.
 * * Complexity:
 * Time Complexity: $O(N \log k)$ where N is total nodes and k is number of sub-lists.
 * Space Complexity: O(k) matching heap scale allocation bounds.
 */
public class MergeKSortedLists {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Sort priority based on absolute node node values descending bounds
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (ListNode listHead : lists) {
            if (listHead != null) minHeap.add(listHead);
        }

        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            curr.next = smallestNode;
            curr = curr.next;

            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1); l1.next = new ListNode(4);
        ListNode l2 = new ListNode(1); l2.next = new ListNode(3);
        ListNode l3 = new ListNode(2); l3.next = new ListNode(6);
        ListNode[] lists = {l1, l2, l3};

        ListNode res = mergeKLists(lists);
        System.out.print("Merged K Lists: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 1 2 3 4 6
        System.out.println();
    }
}