/**
 * PROBLEM: Reverse Nodes in Even Length Groups
 * * You are given the head of a linked list. The nodes are assigned to groups sequentially 
 * (1st group size 1, 2nd group size 2, 3rd group size 3, etc.). The last group may be incomplete.
 * Reverse the nodes in each group if and only if that group has an even number of nodes.
 * * Strategy: Local Chunk Look-Ahead
 * Track the target group length, incrementing it at each step. Scan ahead to find the actual 
 * number of nodes remaining in the current group. If that count is even, reverse the group in-place; 
 * otherwise, skip it.
 */
public class ReverseNodesInEvenLengthGroups {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode prev = head; // End of the previous group
        int groupLen = 2;

        while (prev.next != null) {
            ListNode curr = prev.next;
            int count = 0;
            
            // Look ahead to check the actual number of nodes in this group
            ListNode check = curr;
            while (check != null && count < groupLen) {
                check = check.next;
                count++;
            }

            if (count % 2 == 0) { // Reverse if the group size is even
                ListNode reversePrev = prev.next;
                ListNode p = curr;
                ListNode pr = null;
                
                for (int i = 0; i < count; i++) {
                    ListNode nextTemp = p.next;
                    p.next = pr;
                    pr = p;
                    p = nextTemp;
                }
                
                prev.next = pr;
                reversePrev.next = p;
                prev = reversePrev;
            } else { // Skip if the group size is odd
                for (int i = 0; i < count; i++) {
                    prev = prev.next;
                }
            }
            groupLen++;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5); head.next = new ListNode(2);
        head.next.next = new ListNode(6); head.next.next.next = new ListNode(3); // Groups: [5] (odd), [2,6] (even), [3] (odd)

        ListNode res = reverseEvenLengthGroups(head); // [5] -> [6,2] -> [3]
        System.out.print("Reordered list: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 5 6 2 3
        System.out.println();
    }
}