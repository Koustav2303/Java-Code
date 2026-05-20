public class AddTwoNumbers {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node addLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new Node(sum % 10);
            current = current.next;
        }
        
        return dummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Represents 342 (2 -> 4 -> 3)
        Node l1 = new Node(2); l1.next = new Node(4); l1.next.next = new Node(3);
        // Represents 465 (5 -> 6 -> 4)
        Node l2 = new Node(5); l2.next = new Node(6); l2.next.next = new Node(4);

        System.out.print("Number 1 (reversed): "); printList(l1);
        System.out.print("Number 2 (reversed): "); printList(l2);

        Node result = addLists(l1, l2);
        
        // 342 + 465 = 807 (7 -> 0 -> 8)
        System.out.print("Sum List (reversed): "); printList(result);
    }
}