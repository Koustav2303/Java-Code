/**
 * PROBLEM: Flatten Sorted Multi-Level List
 * * Given a linked list where every node represents a sorted linked list containing a 'next' and a 'down' pointer, 
 * flatten the multi-level sorted list structure into a single sorted singly linked list.
 * * Strategy: Recursive Dual-Chain Merging
 * Treat the down-and-right matrix framework as a recursive merge sort problem. 
 * Recursively call flatten on the 'next' node neighbor, then perform a standard sorted merge step 
 * combining the current 'down' sublist with the downstream flattened components.
 */
public class FlattenSortedMultiLevelList {
    static class Node {
        int val; Node next; Node down;
        Node(int val) { this.val = val; }
    }

    public static Node flatten(Node root) {
        if (root == null || root.next == null) return root;

        // Recurse completely to the right side boundary first
        root.next = flatten(root.next);

        // Merge current vertical sublist down layer with the flattened right balance list
        root = mergeSortedLists(root, root.next);
        return root;
    }

    private static Node mergeSortedLists(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node result;
        if (a.val <= b.val) {
            result = a;
            result.down = mergeSortedLists(a.down, b); // Splice vertical down path references
        } else {
            result = b;
            result.down = mergeSortedLists(a, b.down);
        }
        result.next = null; // Unlink horizontal neighbor links during alignment
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(5); root.down = new Node(10);
        Node rightNode = new Node(7); root.next = rightNode; // Matrix setup: 5->7, with 5 dropping down to 10

        Node res = flatten(root);
        System.out.print("Flattened 2D Sort: ");
        while (res != null) { System.out.print(res.val + " "); res = res.down; } // 5 7 10
        System.out.println();
    }
}