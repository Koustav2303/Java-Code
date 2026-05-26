import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
    }

    private static HashMap<Node, Node> visited = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        // If the node was already cloned, return the clone from the map
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);

        // Iterate through the neighbors to copy them recursively
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        
        return cloneNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        
        Node cloned = cloneGraph(node1);
        System.out.println("Cloned root value: " + cloned.val);
    }
}