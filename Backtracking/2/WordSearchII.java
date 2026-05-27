import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    private static void dfs(char[][] board, int i, int j, TrieNode p, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) return;
        
        p = p.children[c - 'a'];
        if (p.word != null) {
            result.add(p.word);
            p.word = null; // Prevent duplicate entries
        }

        board[i][j] = '#'; // Mark visited
        
        dfs(board, i - 1, j, p, result); 
        dfs(board, i + 1, j, p, result);
        dfs(board, i, j - 1, p, result);
        dfs(board, i, j + 1, p, result);
        
        board[i][j] = c; // Backtrack
    }

    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) p.children[i] = new TrieNode();
                p = p.children[i];
            }
            p.word = w; // Store the full word at the leaf
        }
        return root;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println("Words found: " + findWords(board, words)); // [oath, eat]
    }
}