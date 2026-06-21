/**
 * PROBLEM: Linked List Radix Sort
 * * Sort a singly linked list of non-negative integers using Radix Sort without allocating new nodes 
 * or converting the list into an intermediate array structure.
 * * Strategy: Head-Tail Pointer Chain Routing
 * Maintain an array of 10 independent pointer pairs (`bucketHead`, `bucketTail`) to represent digit buckets 
 * 0 through 9. Traverse the linked list sequentially, detaching each node and linking it to the tail of its 
 * matching digit bucket. Conclude each digit position pass by stitching the tails of the buckets 
 * to the heads of the subsequent buckets in-place to rebuild the unified list chain.
 */
public class LinkedListRadixSort {
    static class Node {
        int val; Node next;
        Node(int val) { this.val = val; }
    }

    public static Node radixSortList(Node head) {
        if (head == null || head.next == null) return head;

        int max = head.val;
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.val > max) max = curr.val;
        }

        // Loop through each digit position exponent multiplier
        for (int exp = 1; max / exp > 0; exp *= 10) {
            Node[] bucketHeads = new Node[10];
            Node[] bucketTails = new Node[10];

            Node curr = head;
            while (curr != null) {
                int digit = (curr.val / exp) % 10;
                
                if (bucketHeads[digit] == null) {
                    bucketHeads[digit] = curr;
                } else {
                    bucketTails[digit].next = curr;
                }
                bucketTails[digit] = curr;
                curr = curr.next;
            }

            // Stitch the independent bucket chains back together in-place
            Node newHead = null, prevTail = null;
            for (int i = 0; i < 10; i++) {
                if (bucketHeads[i] != null) {
                    if (newHead == null) {
                        newHead = bucketHeads[i];
                    } else {
                        prevTail.next = bucketHeads[i];
                    }
                    prevTail = bucketTails[i];
                }
            }
            if (prevTail != null) prevTail.next = null; // Terminate list chain cleanly
            head = newHead;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(170); head.next = new Node(45); head.next.next = new Node(90);
        
        Node res = radixSortList(head);
        System.out.print("Linked List Radix Sorted: ");
        while (res != null) { System.out.print(res.val + " "); res = res.next; } // 45 90 170
        System.out.println();
    }
}