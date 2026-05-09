class Node {
    int data;
    Node next;
    Node(int d) { data = d; next = null; }
}

public class NodeManager {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        
        // Insert at head
        Node newNode = new Node(0);
        newNode.next = head;
        head = newNode;

        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}