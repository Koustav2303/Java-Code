import java.util.HashMap;

public class ConstructTreeInPost {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static int postIndex;
    static HashMap<Integer, Integer> inorderMap;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>();
        
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(postorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] postorder, int left, int right) {
        if (left > right) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int splitIndex = inorderMap.get(rootVal);

        // MUST build right subtree first, because postorder evaluates right before left
        root.right = build(postorder, splitIndex + 1, right);
        root.left = build(postorder, left, splitIndex - 1);

        return root;
    }

    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = buildTree(inorder, postorder);
        System.out.print("Reconstructed Inorder: ");
        printInorder(root);
        System.out.println();
    }
}