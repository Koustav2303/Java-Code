/**
 * PROBLEM: Flatten a Multilevel Doubly Linked List
 * * Given a doubly linked list where nodes have a child pointer pointing to a separate multilevel list, 
 * flatten the list so that all the nodes appear in a single-level doubly linked list sequence.
 * * Strategy: In-Place Splice Navigation
 * Traverse the list. When a node with a 'child' reference is hit, find the tail of that child chain. 
 * Splice the child chain directly between the current node and its original 'next' neighbor. 
 * Remember to null-out the child pointer after splicing to clean the structure.
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 */
public class FlattenMultilevelList {
    static class Node {
        int val; Node prev, next, child;
        Node(int val) { this.val = val; }
    }

    public static Node flatten(Node head) {
        if (head == null) return null;
        Node curr = head;

        while (curr != null) {
            if (curr.child != null) {
                Node nextNeighbor = curr.next;
                Node childHead = flatten(curr.child); // Flatten down recursively first

                // Link current node to the head of the child list
                curr.next = childHead;
                childHead.prev = curr;
                curr.child = null; // Sever child structural link pointer

                // Locate the tail of the child chain
                Node childTail = childHead;
                while (childTail.next != null) {
                    childTail = childTail.next;
                }

                // Connect child tail back to the original next neighbor segment
                childTail.next = nextNeighbor;
                if (nextNeighbor != null) {
                    nextNeighbor.prev = childTail;
                }
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1); Node n2 = new Node(2); head.next = n2; n2.prev = head;
        Node childNode = new Node(3); n2.child = childNode; // Node 2 contains a child branch pointing to 3

        Node res = flatten(head);
        System.out.print("Flattened Sequence: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 1 2 3
        System.out.println();
    }
}