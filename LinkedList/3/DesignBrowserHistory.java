/**
 * PROBLEM: Design Browser History
 * * You have a browser of one tab where you start on the homepage and you can visit another url, 
 * visit back in history number of steps or forward in history number of steps.
 * * Strategy: Custom Doubly Linked List Configuration
 * Model the history trail using explicit Doubly Linked Nodes with 'prev' and 'next' pointers. 
 * Visiting a new page cuts off all forward history steps by decoupling the trailing next reference chain.
 */
public class DesignBrowserHistory {
    static class DLLNode {
        String url; DLLNode prev, next;
        DLLNode(String url) { this.url = url; }
    }

    private DLLNode current;

    public DesignBrowserHistory(String homepage) {
        current = new DLLNode(homepage);
    }
    
    public void visit(String url) {
        DLLNode newNode = new DLLNode(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode; // Clear any existing forward navigation timeline bounds
    }
    
    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }
    
    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        DesignBrowserHistory browser = new DesignBrowserHistory("leetcode.com");
        browser.visit("google.com");
        browser.visit("github.com");
        System.out.println("Back 1 step: " + browser.back(1));       // google.com
        System.out.println("Forward 1 step: " + browser.forward(1)); // github.com
    }
}