public class MaxPathSumTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    static int globalMax = Integer.MIN_VALUE;

    public static int maxPathSum(Node root) {
        globalMax = Integer.MIN_VALUE;
        calculateMaxPath(root);
        return globalMax;
    }

    private static int calculateMaxPath(Node node) {
        if (node == null) return 0;

        // Ignore paths that give negative sums
        int leftMax = Math.max(0, calculateMaxPath(node.left));
        int rightMax = Math.max(0, calculateMaxPath(node.right));

        // Calculate the max path THROUGH this current node (forming an arch)
        globalMax = Math.max(globalMax, leftMax + rightMax + node.data);

        // Return the max path extending downwards from this node
        return Math.max(leftMax, rightMax) + node.data;
    }

    public static void main(String[] args) {
        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7); // Max path is 15 -> 20 -> 7 = 42

        System.out.println("Maximum Path Sum: " + maxPathSum(root));
    }
}