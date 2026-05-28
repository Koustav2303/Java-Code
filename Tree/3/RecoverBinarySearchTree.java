public class RecoverBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static TreeNode first = null, second = null, prev = null;

    public static void recoverTree(TreeNode root) {
        first = null; second = null; prev = new TreeNode(Integer.MIN_VALUE);
        traverse(root);
        
        // Swap values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private static void traverse(TreeNode root) {
        if (root == null) return;
        
        traverse(root.left);
        
        // If the previous node is strictly greater, we found an anomaly
        if (first == null && prev.val > root.val) first = prev;
        if (first != null && prev.val > root.val) second = root;
        
        prev = root;
        traverse(root.right);
    }

    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left); System.out.print(node.val + " "); printInOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // 3 and 2 are swapped

        System.out.print("Before recovery: "); printInOrder(root); System.out.println();
        recoverTree(root);
        System.out.print("After recovery: "); printInOrder(root); System.out.println();
    }
}