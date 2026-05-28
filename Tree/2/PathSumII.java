import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(TreeNode node, int remain, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;

        currentPath.add(node.val);
        
        // If it's a leaf node and the sum matches, add a copy of the path to the results
        if (node.left == null && node.right == null && remain == node.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            backtrack(node.left, remain - node.val, currentPath, result);
            backtrack(node.right, remain - node.val, currentPath, result);
        }
        
        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        
        System.out.println("Paths summing to 22: " + pathSum(root, 22)); 
        // [[5, 4, 11, 2]]
    }
}