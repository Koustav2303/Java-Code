/**
 * PROBLEM: Backspace String Compare
 * * Given two strings s and t, return true if they are equal when both are typed into empty text editors. 
 * '#' means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 * * Example:
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * * Approach: Traverse both strings backwards using two pointers, keeping track of how many 
 * backspaces we've seen so we know which characters to skip.
 */
public class BackspaceStringCompare {
    public static boolean backspaceCompare(String s, String t) {
        int p1 = s.length() - 1;
        int p2 = t.length() - 1;
        
        int skipS = 0;
        int skipT = 0;
        
        while (p1 >= 0 || p2 >= 0) {
            // Find the next valid character in string S
            while (p1 >= 0) {
                if (s.charAt(p1) == '#') { skipS++; p1--; }
                else if (skipS > 0) { skipS--; p1--; }
                else break;
            }
            
            // Find the next valid character in string T
            while (p2 >= 0) {
                if (t.charAt(p2) == '#') { skipT++; p2--; }
                else if (skipT > 0) { skipT--; p2--; }
                else break;
            }
            
            // Compare the valid characters
            if (p1 >= 0 && p2 >= 0 && s.charAt(p1) != t.charAt(p2)) {
                return false;
            }
            
            // If one string is exhausted but the other isn't
            if ((p1 >= 0) != (p2 >= 0)) {
                return false;
            }
            
            p1--;
            p2--;
        }
        
        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c", "ad#c")); // true
        System.out.println(backspaceCompare("a##c", "#a#c")); // true
    }
}