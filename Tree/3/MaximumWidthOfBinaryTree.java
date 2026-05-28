import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class Pair {
        TreeNode node;
        int index;
        Pair(TreeNode n, int i) { node = n; index = i; }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = queue.peek().index;
            int end = start;

            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                TreeNode node = p.node;
                end = p.index;

                if (node.left != null) queue.add(new Pair(node.left, 2 * end));
                if (node.right != null) queue.add(new Pair(node.right, 2 * end + 1));
            }
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.right = new TreeNode(2);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        System.out.println("Maximum width: " + widthOfBinaryTree(root)); // 4
    }
}