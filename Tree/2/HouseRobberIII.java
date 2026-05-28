public class HouseRobberIII {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int rob(TreeNode root) {
        int[] result = robSubtree(root);
        return Math.max(result[0], result[1]);
    }

    // Returns [robbed_max, skipped_max]
    private static int[] robSubtree(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = robSubtree(node.left);
        int[] right = robSubtree(node.right);

        // If we rob this node, we CANNOT rob its children
        int robThis = node.val + left[1] + right[1];
        
        // If we skip this node, we can EITHER rob or skip its children (whichever is higher)
        int skipThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{robThis, skipThis};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(3); root.right.right = new TreeNode(1);

        System.out.println("Max money robbed: " + rob(root)); // 7 (3 + 3 + 1)
    }
}