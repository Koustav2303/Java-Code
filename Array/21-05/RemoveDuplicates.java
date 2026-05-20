public class RemoveDuplicates {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node deleteDuplicates(Node head) {
        Node current = head;
        
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                // Skip the next node
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1); head.next = new Node(1); head.next.next = new Node(2);
        head.next.next.next = new Node(3); head.next.next.next.next = new Node(3);
        
        System.out.print("Original with duplicates: "); printList(head);
        
        head = deleteDuplicates(head);
        
        System.out.print("Duplicates removed: "); printList(head);
    }
}