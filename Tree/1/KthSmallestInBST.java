public class KthSmallestInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int count = 0;
    private static int result = -1;

    public static int kthSmallest(TreeNode root, int k) {
        count = 0;
        inOrderTraverse(root, k);
        return result;
    }

    private static void inOrderTraverse(TreeNode node, int k) {
        if (node == null) return;

        inOrderTraverse(node.left, k);
        
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        inOrderTraverse(node.right, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println("2nd smallest element is: " + kthSmallest(root, 2)); // 2
    }
}