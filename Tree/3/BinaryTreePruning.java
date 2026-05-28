public class BinaryTreePruning {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        // If it's a leaf node and its value is 0, prune it (return null to parent)
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    public static void printPreOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreOrder(node.left); printPreOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(1);

        System.out.print("Pruned Tree: "); printPreOrder(pruneTree(root)); System.out.println();
    }
}