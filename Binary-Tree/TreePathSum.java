public class TreePathSum {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    public static boolean hasPathSum(Node root, int targetSum) {
        if (root == null) return false;

        // If we reach a leaf node, check if the remaining target sum equals its value
        if (root.left == null && root.right == null) {
            return targetSum == root.data;
        }

        // Recursively check the left and right subtrees with the subtracted sum
        return hasPathSum(root.left, targetSum - root.data) || 
               hasPathSum(root.right, targetSum - root.data);
    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(11);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2); // Path: 5 -> 4 -> 11 -> 2 = 22

        int target = 22;
        System.out.println("Has path summing to " + target + "? " + hasPathSum(root, target));
    }
}