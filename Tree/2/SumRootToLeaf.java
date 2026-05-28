public class SumRootToLeaf {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;

        // Shift existing digits left and add the new digit
        currentSum = currentSum * 10 + node.val;

        // If it's a leaf, return the fully formed number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println("Sum of all paths: " + sumNumbers(root)); // 12 + 13 = 25
    }
}