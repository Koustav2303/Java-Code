import java.util.Stack;

/**
 * PROBLEM: Simplify Path
 * * Given an absolute path for a Unix-style file system, simplify it. Transform it into the simplified canonical path.
 * * Rules:
 * - A period '.' refers to the current directory.
 * - A double period '..' moves the directory up a level.
 * - Multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * * Example:
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Input: path = "/../"
 * Output: "/"
 * * Complexity:
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class SimplifyPath {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        
        for (String directory : components) {
            // Ignore empty strings caused by sequential slashes and current directory reference '.'
            if (directory.isEmpty() || ".".equals(directory)) {
                continue;
            }
            if ("..".equals(directory)) {
                if (!stack.isEmpty()) {
                    stack.pop(); // Pop up one directory tier if stack isn't empty
                }
            } else {
                stack.push(directory); // Push valid folder name
            }
        }
        
        // Reconstruct the structural layout
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Simplified: " + simplifyPath("/home//foo/")); // "/home/foo"
        System.out.println("Simplified: " + simplifyPath("/../"));        // "/"
    }
}