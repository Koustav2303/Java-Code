import java.util.HashMap;

public class BuildTreeFromTraversals {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    static int preorderIndex;
    static HashMap<Integer, Integer> inorderMap;

    public static Node buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderMap = new HashMap<>();
        
        // Cache inorder values and indices for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return arrayToTree(preorder, 0, inorder.length - 1);
    }

    private static Node arrayToTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        // The current root is always the next element in the preorder array
        int rootValue = preorder[preorderIndex++];
        Node root = new Node(rootValue);

        // Find where this root splits the inorder array
        int inorderIndex = inorderMap.get(rootValue);

        // Build left and right subtrees
        root.left = arrayToTree(preorder, left, inorderIndex - 1);
        root.right = arrayToTree(preorder, inorderIndex + 1, right);

        return root;
    }

    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        
        Node root = buildTree(preorder, inorder);
        
        System.out.print("Reconstructed Tree (Inorder view): ");
        printInorder(root);
        System.out.println();
    }
}