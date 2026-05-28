public class DeleteNodeInBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node to delete found
            
            // Case 1 & 2: No child or exactly one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: Two children. Find the min value in the right subtree
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val; // Overwrite value
            root.right = deleteNode(root.right, root.val); // Delete the duplicate min node
        }
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.print("Before delete: "); printInOrder(root); System.out.println();
        
        root = deleteNode(root, 3);
        System.out.print("After deleting 3: "); printInOrder(root); System.out.println();
    }
}