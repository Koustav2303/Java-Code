import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Design Browser History
 * * You have a browser of one tab where you start on the homepage.
 * - BrowserHistory(string homepage) Initializes the object with the homepage.
 * - void visit(string url) Visits url from the current page. Clears all forward history.
 * - string back(int steps) Move steps back in history. Return the current url.
 * - string forward(int steps) Move steps forward in history. Return the current url.
 * * Approach:
 * While you could use two stacks, using a dynamic array (List) and an index pointer is 
 * much faster for traversing exact steps in O(1) time.
 */
public class DesignBrowserHistory {
    private List<String> history;
    private int currentIndex;
    private int maxIndex;

    public DesignBrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
        maxIndex = 0;
    }
    
    public void visit(String url) {
        currentIndex++;
        // If we are overwriting history, resize via the pointer
        if (currentIndex < history.size()) {
            history.set(currentIndex, url);
        } else {
            history.add(url);
        }
        // Clears forward history virtually by capping the max index
        maxIndex = currentIndex;
    }
    
    public String back(int steps) {
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }
    
    public String forward(int steps) {
        currentIndex = Math.min(maxIndex, currentIndex + steps);
        return history.get(currentIndex);
    }

    public static void main(String[] args) {
        DesignBrowserHistory browser = new DesignBrowserHistory("leetcode.com");
        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");
        System.out.println(browser.back(1)); // facebook.com
        System.out.println(browser.back(1)); // google.com
        System.out.println(browser.forward(1)); // facebook.com
        browser.visit("linkedin.com"); // Clears forward history (youtube.com is gone)
        System.out.println(browser.forward(2)); // linkedin.com (can't go further)
        System.out.println(browser.back(2)); // google.com
    }
}