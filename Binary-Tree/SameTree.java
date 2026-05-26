public class SameTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public static boolean isSameTree(Node p, Node q) {
        // If both are null, they are identical up to this point
        if (p == null && q == null) return true;
        
        // If only one is null, or their values differ, they are not identical
        if (p == null || q == null || p.data != q.data) return false;
        
        // Recursively check left and right branches
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        Node tree1 = new Node(1);
        tree1.left = new Node(2);
        tree1.right = new Node(3);

        Node tree2 = new Node(1);
        tree2.left = new Node(2);
        tree2.right = new Node(3);

        System.out.println("Are the trees identical? " + isSameTree(tree1, tree2));
    }
}