import java.util.*;

public class VerticalOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class Point {
        TreeNode node;
        int row, col;
        Point(TreeNode n, int r, int c) { node = n; row = r; col = c; }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Map: Column -> PriorityQueue of Points
        TreeMap<Integer, PriorityQueue<Point>> map = new TreeMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(root, 0, 0));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            
            map.putIfAbsent(p.col, new PriorityQueue<>((a, b) -> {
                // If in same row and col, sort by value. Otherwise, sort by row.
                if (a.row == b.row) return a.node.val - b.node.val;
                return a.row - b.row;
            }));
            map.get(p.col).add(p);

            if (p.node.left != null) queue.add(new Point(p.node.left, p.row + 1, p.col - 1));
            if (p.node.right != null) queue.add(new Point(p.node.right, p.row + 1, p.col + 1));
        }

        for (PriorityQueue<Point> pq : map.values()) {
            List<Integer> colList = new ArrayList<>();
            while (!pq.isEmpty()) colList.add(pq.poll().node.val);
            result.add(colList);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);

        System.out.println("Vertical Order: " + verticalTraversal(root));
        // [[9], [3, 15], [20], [7]]
    }
}