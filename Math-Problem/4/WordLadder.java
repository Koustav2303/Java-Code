import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int changes = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return changes;

                // Try changing every character to 'a' through 'z'
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] arr = word.toCharArray();
                        arr[j] = c;
                        String nextWord = new String(arr);
                        
                        if (dict.contains(nextWord)) {
                            queue.add(nextWord);
                            dict.remove(nextWord); // Prevent revisiting
                        }
                    }
                }
            }
            changes++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String begin = "hit", end = "cog";
        List<String> dict = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Shortest ladder length: " + ladderLength(begin, end, dict));
    }
}