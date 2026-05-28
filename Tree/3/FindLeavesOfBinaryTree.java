import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        getHeight(root, result);
        return result;
    }

    private static int getHeight(TreeNode node, List<List<Integer>> result) {
        if (node == null) return -1;
        
        int height = 1 + Math.max(getHeight(node.left, result), getHeight(node.right, result));
        
        if (result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        return height;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);

        System.out.println("Leaves grouped by removal stage: " + findLeaves(root));
        // [[4, 5, 3], [2], [1]]
    }
}