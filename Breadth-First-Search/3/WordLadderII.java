import java.util.*;

public class WordLadderII {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) return res;

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(beginWord, endWord, dict, graph, distance);
        dfs(endWord, beginWord, graph, distance, new ArrayList<>(Arrays.asList(endWord)), res);
        
        return res;
    }

    private static void bfs(String beginWord, String endWord, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int curDist = distance.get(curr);
            if (curr.equals(endWord)) break;

            char[] chars = curr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char old = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    String next = new String(chars);
                    if (dict.contains(next)) {
                        graph.computeIfAbsent(next, k -> new ArrayList<>()).add(curr);
                        if (!distance.containsKey(next)) {
                            distance.put(next, curDist + 1);
                            queue.add(next);
                        }
                    }
                }
                chars[i] = old;
            }
        }
    }

    private static void dfs(String curr, String beginWord, Map<String, List<String>> graph, Map<String, Integer> distance, List<String> path, List<List<String>> res) {
        if (curr.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            res.add(validPath);
            return;
        }
        if (!graph.containsKey(curr)) return;

        for (String parent : graph.get(curr)) {
            if (distance.get(parent) + 1 == distance.get(curr)) {
                path.add(parent);
                dfs(parent, beginWord, graph, distance, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(findLadders(beginWord, endWord, wordList));
    }
}