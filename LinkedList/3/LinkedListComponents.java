import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Linked List Components
 * * Given the head of a linked list containing unique values and an integer array nums which is a subset 
 * of the linked list values, return the number of connected components in nums.
 * * Strategy: Boundary Intersection Sieve
 * Load the elements of `nums` into a HashSet for $O(1)$ lookups. Traverse the list. A component boundary 
 * is completed whenever the current node's value exists in the set AND the next node is either null 
 * or its value does not exist in the set.
 */
public class LinkedListComponents {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int componentsCount = 0;
        ListNode curr = head;

        while (curr != null) {
            // A component ends if current is in the subset but the next item breaks the sequence chain
            if (set.contains(curr.val) && (curr.next == null || !set.contains(curr.next.val))) {
                componentsCount++;
            }
            curr = curr.next;
        }
        return componentsCount;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0); head.next = new ListNode(1);
        head.next.next = new ListNode(2); head.next.next.next = new ListNode(3);
        int[] nums = {0, 1, 3};

        System.out.println("Connected Components count: " + numComponents(head, nums)); // 2 -> Components are [0,1] and [3]
    }
}