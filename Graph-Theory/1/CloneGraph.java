import java.util.*;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val) { val = _val; neighbors = new ArrayList<>(); }
    }

    private static Map<Node, Node> visited = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);
        
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);
        
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
        System.out.println("Original Node 1 val: " + node1.val + ", Cloned Node 1 val: " + cloned.val);
        System.out.println("Are they the exact same object in memory? " + (node1 == cloned)); // false
    }
}