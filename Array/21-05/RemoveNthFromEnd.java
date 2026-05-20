public class RemoveNthFromEnd {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node removeNth(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node slow = dummy;
        Node fast = dummy;

        // Give fast pointer an N-step head start
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both at same speed
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Skip the Nth node
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(10); head.next = new Node(20); head.next.next = new Node(30);
        head.next.next.next = new Node(40); head.next.next.next.next = new Node(50);
        
        System.out.print("Original: "); printList(head);
        
        head = removeNth(head, 2); // Remove 2nd from end (the '40')
        
        System.out.print("Removed 2nd from end: "); printList(head);
    }
}