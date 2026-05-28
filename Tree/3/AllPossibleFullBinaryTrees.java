import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPossibleFullBinaryTrees {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public static List<TreeNode> allPossibleFBT(int n) {
        // Even numbers cannot form full binary trees
        if (n % 2 == 0) return new ArrayList<>();
        if (memo.containsKey(n)) return memo.get(n);

        List<TreeNode> list = new ArrayList<>();
        if (n == 1) {
            list.add(new TreeNode(0));
            return list;
        }

        // Subtract 1 for the root node, then distribute remaining across left/right
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> leftSubtrees = allPossibleFBT(i);
            List<TreeNode> rightSubtrees = allPossibleFBT(n - 1 - i);

            // Cartesian product of left and right subtrees
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        memo.put(n, list);
        return list;
    }

    public static void main(String[] args) {
        int n = 7;
        List<TreeNode> trees = allPossibleFBT(n);
        System.out.println("Total possible full binary trees for n=" + n + ": " + trees.size()); // 5
    }
}