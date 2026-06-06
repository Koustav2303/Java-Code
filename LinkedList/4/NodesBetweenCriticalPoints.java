import java.util.Arrays;

/**
 * PROBLEM: Find the Minimum and Maximum Number of Nodes Between Critical Points
 * * A critical point in a linked list is defined as either a local maxima or a local minima.
 * Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] 
 * tracking the coordinate gaps between any distinct critical points. If there are fewer than 
 * two critical points, return [-1, -1].
 * * Strategy: Triple-Node Index Tracking
 * Maintain pointers for `prev`, `curr`, and `curr.next` to evaluate inflection trends on the fly. 
 * Track the positions of the first and most recent critical points found. 
 * Compute the maximum distance using the absolute gap between the first and last critical points, 
 * and update the minimum distance progressively between consecutive critical points.
 */
public class NodesBetweenCriticalPoints {
    static class ListNode {
        int val; ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            boolean isMaximal = curr.val > prev.val && curr.val > curr.next.val;
            boolean isMinimal = curr.val < prev.val && curr.val < curr.next.val;

            if (isMaximal || isMinimal) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }
            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // If no two critical points exist, return error flag matrix array
        if (firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3); head.next = new ListNode(1); // Minima at index 1
        head.next.next = new ListNode(5); head.next.next.next = new ListNode(1); // Maxima at index 2, Minima at index 3
        head.next.next.next.next = new ListNode(5); // Maxima at index 4

        System.out.println("Distances: " + Arrays.toString(nodesBetweenCriticalPoints(head))); // [1, 3]
    }
}