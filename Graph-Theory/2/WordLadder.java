import java.util.*;

/**
 * PROBLEM: Word Ladder
 * * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence 
 * of words beginWord -> s1 -> s2 -> ... -> sk such that every adjacent pair differs by exactly one letter.
 * Return the number of words in the shortest transformation sequence, or 0 if no such sequence exists.
 * * Strategy:
 * Unweighted Shortest Path on implicit graphs. We use Breadth-First Search (BFS).
 * Instead of checking against every word in the dictionary (which is O(N)), we mutate every character of 
 * the current word from 'a' to 'z' and check if the resulting variant is inside a lookup HashSet.
 * * Complexity:
 * Time Complexity: O(M^2 * N) where M is word length and N is dictionary size.
 * Space Complexity: O(M * N)
 */
public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int transformations = 1;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) return transformations;
                
                char[] chars = curr.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[j] = c;
                        String nextWord = new String(chars);
                        
                        if (dict.contains(nextWord)) {
                            queue.add(nextWord);
                            dict.remove(nextWord); // Prevent cycles by immediate elimination
                        }
                    }
                    chars[j] = originalChar; // Restore structural layout
                }
            }
            transformations++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int length = ladderLength("hit", "cog", wordList);
        System.out.println("Shortest word transformation steps: " + length); // 5
    }
}