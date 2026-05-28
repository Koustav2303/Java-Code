public class CountCompleteNodes {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root, true);
        int rightDepth = getDepth(root, false);

        // If depths are equal, it's a perfect binary tree
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1; // 2^depth - 1
        }

        // Otherwise, recursively count
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int getDepth(TreeNode node, boolean isLeft) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = isLeft ? node.left : node.right;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println("Total nodes: " + countNodes(root)); // 6
    }
}