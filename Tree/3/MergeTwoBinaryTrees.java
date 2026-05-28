public class MergeTwoBinaryTrees {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        // Overlay t2 onto t1
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        
        return t1;
    }

    public static void printPreOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreOrder(node.left); printPreOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3); t1.right = new TreeNode(2); t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1); t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4); t2.right.right = new TreeNode(7);

        TreeNode merged = mergeTrees(t1, t2);
        System.out.print("Merged Tree PreOrder: "); printPreOrder(merged); System.out.println();
    }
}