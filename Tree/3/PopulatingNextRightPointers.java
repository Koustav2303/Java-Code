public class PopulatingNextRightPointers {
    static class Node {
        public int val;
        public Node left, right, next;
        public Node(int _val) { val = _val; }
    }

    public static Node connect(Node root) {
        if (root == null) return null;
        Node leftmost = root;

        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                // Connect the two children of the same parent
                head.left.next = head.right;
                
                // Connect the right child to the left child of the NEXT parent
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next; // Move horizontally
            }
            leftmost = leftmost.left; // Move down to the next level
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2); root.right = new Node(3);
        root.left.left = new Node(4); root.left.right = new Node(5);
        root.right.left = new Node(6); root.right.right = new Node(7);

        connect(root);
        System.out.println("Node 5's next is: " + root.left.right.next.val); // 6
    }
}