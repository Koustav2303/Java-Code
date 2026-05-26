public class LowestCommonAncestorGeneral {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
        // Base case: we hit the bottom, or we found one of the targets
        if (root == null || root == p || root == q) return root;

        // Search left and right subtrees
        Node leftLCA = lowestCommonAncestor(root.left, p, q);
        Node rightLCA = lowestCommonAncestor(root.right, p, q);

        // If both sides returned a node, the targets are split! This root is the LCA.
        if (leftLCA != null && rightLCA != null) return root;

        // Otherwise, return whichever side actually found something
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        Node p = new Node(5);
        Node q = new Node(1);
        
        root.left = p;
        root.right = q;
        root.left.left = new Node(6);
        root.left.right = new Node(2);

        Node lca = lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor of 5 and 1 is: " + lca.data);
    }
}