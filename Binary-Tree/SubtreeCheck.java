public class SubtreeCheck {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public static boolean isSubtree(Node root, Node subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;

        // If the trees match perfectly from this node down, we found it
        if (isSameTree(root, subRoot)) return true;

        // Otherwise, keep looking in the left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.data != q.data) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(4);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(2);

        Node subRoot = new Node(4);
        subRoot.left = new Node(1);
        subRoot.right = new Node(2);

        System.out.println("Is subRoot a subtree of root? " + isSubtree(root, subRoot));
    }
}