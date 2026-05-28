import java.util.*;

public class DistanceKNodes {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        
        queue.add(target);
        visited.add(target);
        
        int currentDistance = 0;
        
        while (!queue.isEmpty()) {
            if (currentDistance == k) break;
            
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                
                // Go Left
                if (curr.left != null && visited.add(curr.left)) queue.add(curr.left);
                // Go Right
                if (curr.right != null && visited.add(curr.right)) queue.add(curr.right);
                // Go Up (to Parent)
                TreeNode parent = parentMap.get(curr);
                if (parent != null && visited.add(parent)) queue.add(parent);
            }
            currentDistance++;
        }
        
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) result.add(queue.poll().val);
        return result;
    }

    private static void buildParentMap(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        if (node == null) return;
        map.put(node, parent);
        buildParentMap(node.left, node, map);
        buildParentMap(node.right, node, map);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode target = new TreeNode(5);
        root.left = target; root.right = new TreeNode(1);
        target.left = new TreeNode(6); target.right = new TreeNode(2);
        target.right.left = new TreeNode(7); target.right.right = new TreeNode(4);

        System.out.println("Nodes at distance 2: " + distanceK(root, target, 2)); // [7, 4, 1]
    }
}