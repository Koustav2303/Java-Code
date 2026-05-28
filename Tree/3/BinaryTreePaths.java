import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) dfs(root, "", paths);
        return paths;
    }

    private static void dfs(TreeNode node, String path, List<String> paths) {
        path += node.val;
        if (node.left == null && node.right == null) {
            paths.add(path); // Leaf node reached
        } else {
            path += "->";
            if (node.left != null) dfs(node.left, path, paths);
            if (node.right != null) dfs(node.right, path, paths);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        System.out.println("Tree Paths: " + binaryTreePaths(root)); // [1->2->5, 1->3]
    }
}