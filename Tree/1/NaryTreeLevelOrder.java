import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTreeLevelOrder {
    static class Node {
        public int val;
        public List<Node> children;
        public Node(int _val) { val = _val; children = new ArrayList<>(); }
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();
                currentLevel.add(curr.val);
                // Add all children to the queue
                for (Node child : curr.children) {
                    if (child != null) queue.add(child);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node c1 = new Node(3); Node c2 = new Node(2); Node c3 = new Node(4);
        root.children.add(c1); root.children.add(c2); root.children.add(c3);
        c1.children.add(new Node(5)); c1.children.add(new Node(6));

        System.out.println("Level Order: " + levelOrder(root));
        // Output: [[1], [3, 2, 4], [5, 6]]
    }
}