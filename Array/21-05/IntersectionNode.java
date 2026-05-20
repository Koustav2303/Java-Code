public class IntersectionNode {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) return null;
        
        Node pA = headA;
        Node pB = headB;
        
        while (pA != pB) {
            // If pA reaches end, redirect it to headB
            pA = (pA == null) ? headB : pA.next;
            // If pB reaches end, redirect it to headA
            pB = (pB == null) ? headA : pB.next;
        }
        
        return pA; // Returns the intersection node, or null if none
    }

    public static void main(String[] args) {
        // Create an intersecting list:
        // A: 1 -> 2 \
        //            3 -> 4
        // B:      5 /
        Node intersection = new Node(3);
        intersection.next = new Node(4);

        Node headA = new Node(1);
        headA.next = new Node(2);
        headA.next.next = intersection;

        Node headB = new Node(5);
        headB.next = intersection;

        Node result = getIntersectionNode(headA, headB);
        System.out.println("The lists intersect at node with value: " + result.data);
    }
}