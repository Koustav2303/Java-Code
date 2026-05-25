import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordBreakIIDP {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }
    
    private static List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        
        List<String> result = new ArrayList<>();
        if (s.isEmpty()) {
            result.add("");
            return result;
        }
        
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subList = dfs(s.substring(word.length()), wordDict, memo);
                for (String sub : subList) {
                    result.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        memo.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println("Possible sentences:");
        for (String sentence : wordBreak(s, dict)) {
            System.out.println("  " + sentence);
        }
    }
}