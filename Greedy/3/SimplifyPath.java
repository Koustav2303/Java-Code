import java.util.Stack;

public class SimplifyPath {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] components = path.split("/");
        
        for (String dir : components) {
            if (dir.equals("..")) {
                if (!stack.isEmpty()) stack.pop(); // Go up a directory
            } else if (!dir.equals(".") && !dir.isEmpty()) {
                stack.push(dir); // Valid directory name
            }
        }
        
        // Rebuild the path
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        return result.length() == 0 ? "/" : result.toString();
    }

    public static void main(String[] args) {
        String path = "/home//foo/../bar/./baz/";
        System.out.println("Original path: " + path);
        System.out.println("Simplified path: " + simplifyPath(path)); // Output: /home/bar/baz
    }
}