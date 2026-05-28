public class BinaryTreeCameras {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static int cameras = 0;

    public static int minCameraCover(TreeNode root) {
        cameras = 0;
        // If the root is completely unmonitored, we MUST place a camera on it
        if (dfs(root) == 0) cameras++;
        return cameras;
    }

    // States: 0 = Unmonitored, 1 = Monitored (no camera), 2 = Has Camera
    private static int dfs(TreeNode node) {
        if (node == null) return 1; // Null nodes are inherently "monitored"

        int left = dfs(node.left);
        int right = dfs(node.right);

        // If any child is unmonitored, place a camera here
        if (left == 0 || right == 0) {
            cameras++;
            return 2;
        }

        // If any child has a camera, this node is monitored
        if (left == 2 || right == 2) {
            return 1;
        }

        // Otherwise, this node is unmonitored (children are monitored but have no cameras)
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        
        System.out.println("Minimum cameras required: " + minCameraCover(root)); // 2
    }
}