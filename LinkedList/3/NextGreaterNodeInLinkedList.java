import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PROBLEM: Next Greater Node In Linked List
 * * For each node in the list, find the value of the next node that has a strictly larger value. 
 * Return an integer array tracking these relationships.
 * * Strategy: Dynamic Array Monotonic Filter
 * First convert the linked list values into a dynamic ArrayList to enable indexing. 
 * Then run a standard monotonic decreasing stack from left to right over the list array to resolve 
 * next-greater targets in linear time.
 */
public class NextGreaterNodeInLinkedList {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int[] res = new int[list.size()];
        Stack<Integer> stack = new Stack<>(); // Holds array indices

        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && list.get(i) > list.get(stack.peek())) {
                int poppedIndex = stack.pop();
                res[poppedIndex] = list.get(i);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2); head.next = new ListNode(1); head.next.next = new ListNode(5);

        int[] res = nextLargerNodes(head);
        System.out.println("Next Greater Nodes array: " + java.util.Arrays.toString(res)); // [5, 5, 0]
    }
}