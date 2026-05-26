public class TreeDiameter {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    static int maxDiameter = 0;

    public static int diameterOfBinaryTree(Node root) {
        maxDiameter = 0; // Reset for safety
        calculateDepth(root);
        return maxDiameter;
    }

    private static int calculateDepth(Node node) {
        if (node == null) return 0;

        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);

        // The diameter THROUGH this node is left + right
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // Return the depth OF this node to its parent
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Diameter of the tree is: " + diameterOfBinaryTree(root));
    }
}