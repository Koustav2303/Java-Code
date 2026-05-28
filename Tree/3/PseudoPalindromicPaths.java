public class PseudoPalindromicPaths {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int bitmask) {
        if (node == null) return 0;
        
        // Toggle the (node.val)-th bit. 1 means odd frequency, 0 means even frequency.
        bitmask ^= (1 << node.val);
        
        if (node.left == null && node.right == null) {
            // A bitmask has at most one bit set to 1 if (bitmask & (bitmask - 1)) == 0
            if ((bitmask & (bitmask - 1)) == 0) return 1;
            return 0;
        }
        
        return dfs(node.left, bitmask) + dfs(node.right, bitmask);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3); root.right = new TreeNode(1);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);

        System.out.println("Palindromic Paths: " + pseudoPalindromicPaths(root)); // 2
    }
}