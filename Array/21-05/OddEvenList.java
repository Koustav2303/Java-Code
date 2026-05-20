public class OddEvenList {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node groupOddEven(Node head) {
        if (head == null) return null;
        
        Node odd = head;
        Node even = head.next;
        Node evenHead = even; // Keep track of even list start to attach later
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead; // Connect odds to evens
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
        Node head = new Node(1); head.next = new Node(2); head.next.next = new Node(3);
        head.next.next.next = new Node(4); head.next.next.next.next = new Node(5);
        
        System.out.print("Original List: "); printList(head);
        
        head = groupOddEven(head);
        
        System.out.print("Odd-Even Grouped: "); printList(head);
    }
}