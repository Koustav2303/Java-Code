import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeDFS(root, sb);
        return sb.toString();
    }

    private static void serializeDFS(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serializeDFS(root.left, sb);
        serializeDFS(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeDFS(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode deserializeDFS(Queue<String> queue, int min, int max) {
        if (queue.isEmpty()) return null;
        int val = Integer.parseInt(queue.peek());
        if (val < min || val > max) return null; // Value belongs in a different branch

        queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserializeDFS(queue, min, val);
        root.right = deserializeDFS(queue, val, max);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        
        String str = serialize(root);
        System.out.println("Serialized: " + str);
        System.out.println("Deserialized Root: " + deserialize(str).val);
    }
}