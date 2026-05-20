public class DetectCycle {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;
        
        Node slow = head;
        Node fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Moves 1 step
            fast = fast.next.next;     // Moves 2 steps
            
            if (slow == fast) {
                return true; // They met! There is a cycle.
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        
        System.out.println("Has cycle (before): " + hasCycle(head));
        
        // Artificially create a cycle: 40 points back to 20
        head.next.next.next.next = head.next;
        
        System.out.println("Has cycle (after creating one): " + hasCycle(head));
    }
}