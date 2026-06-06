/**
 * PROBLEM: Insert into a Sorted Circular Linked List
 * * Given a node from a Circular Linked List which is sorted in ascending order, write a function 
 * to insert a value insertVal into the list such that it remains sorted.
 * * Strategy: Cyclic Boundary Evaluation
 * Traverse the loop with a single pointer. Insert the new node if:
 * 1. The value falls between consecutive nodes: `curr.val <= insertVal <= curr.next.val`.
 * 2. We reach the wrap-around point (max element tail pointing to min element head) and the value 
 * is either greater than the maximum or smaller than the minimum.
 * 3. We complete a full loop without finding a match (all nodes share identical values).
 */
public class InsertSortedCircularList {
    static class Node {
        int val; Node next;
        Node(int val) { this.val = val; this.next = this; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }

    public static Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        Node curr = head;
        while (true) {
            // Case 1: Standard middle insertion
            if (curr.val <= insertVal && insertVal <= curr.next.val) break;

            // Case 2: Tail-to-head wrap-around connection point
            if (curr.val > curr.next.val) {
                if (insertVal >= curr.val || insertVal <= curr.next.val) break;
            }

            curr = curr.next;
            if (curr == head) break; // Case 3: Completed full loop (all elements are duplicates)
        }

        curr.next = new Node(insertVal, curr.next);
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        Node n2 = new Node(4); head.next = n2;
        Node n3 = new Node(1); n2.next = n3; n3.next = head; // 3 -> 4 -> 1 -> (loops to 3)

        insert(head, 2); // Spliced between 1 and 3
        System.out.println("Node next to 1: " + n3.next.val); // 2
    }
}