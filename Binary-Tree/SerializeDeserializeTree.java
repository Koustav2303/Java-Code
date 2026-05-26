import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        if (root == null) return "null,";
        
        // Pre-order traversal
        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);
        
        return root.data + "," + leftSerialized + rightSerialized;
    }

    // Decodes your encoded data to tree.
    public static Node deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTreeFromQueue(queue);
    }

    private static Node buildTreeFromQueue(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("null")) return null;
        
        Node node = new Node(Integer.parseInt(val));
        node.left = buildTreeFromQueue(queue);
        node.right = buildTreeFromQueue(queue);
        
        return node;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        String serializedString = serialize(root);
        System.out.println("Serialized Data: " + serializedString);

        Node rebuiltTree = deserialize(serializedString);
        System.out.println("Deserialized Root Value: " + rebuiltTree.data);
    }
}