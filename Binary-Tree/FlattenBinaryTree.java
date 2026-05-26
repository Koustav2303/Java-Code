public class FlattenBinaryTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    static Node prev = null;

    public static void flatten(Node root) {
        if (root == null) return;
        
        // Traverse in reverse pre-order (Right, Left, Root)
        flatten(root.right);
        flatten(root.left);
        
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void printRightPath(Node root) {
        while (root != null) {
            System.out.print(root.data + " -> ");
            root = root.right;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);

        flatten(root);
        
        System.out.print("Flattened Tree: ");
        printRightPath(root);
    }
}