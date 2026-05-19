public class LinkedListMergeSort {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    public static Node sortedMerge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node result;
        if (a.data <= b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    public static Node getMiddle(Node head) {
        if (head == null) return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;

        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null; // Break the list into two

        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(15);
        head.next = new Node(10);
        head.next.next = new Node(5);
        head.next.next.next = new Node(20);
        head.next.next.next.next = new Node(3);

        System.out.print("Original List: ");
        printList(head);

        head = mergeSort(head);

        System.out.print("Sorted List:   ");
        printList(head);
    }
}