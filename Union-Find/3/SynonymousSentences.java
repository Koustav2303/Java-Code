import java.util.*;

/**
 * PROBLEM: Synonymous Sentences
 * * You are given a list of equivalent word pairs synonyms and a sentence text.
 * Return all possible synonymous sentences sorted lexicographically.
 * * Strategy: Equivalence Set Backtracking
 * Map words to connected component roots using a string-based DSU. Group all unique synonyms 
 * by their root components and sort them alphabetically. Split the target text sentence into individual words. 
 * Use a recursive backtracking loop to construct all valid word combinations for each position.
 */
public class SynonymousSentences {
    static class SynonymDSU {
        Map<String, String> parent = new HashMap<>();

        public String find(String s) {
            if (!parent.containsKey(s)) parent.put(s, s);
            if (parent.get(s).equals(s)) return s;
            parent.put(s, find(parent.get(s)));
            return parent.get(s);
        }

        public void union(String s1, String s2) {
            String r1 = find(s1), r2 = find(s2);
            if (!r1.equals(r2)) parent.put(r1, r2);
        }
    }

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {
        SynonymDSU dsu = new SynonymDSU();
        for (List<String> pair : synonyms) {
            dsu.union(pair.get(0), pair.get(1));
        }

        // Cluster matching vocabulary terms together by component root
        Map<String, TreeSet<String>> componentsMap = new HashMap<>();
        for (String word : dsu.parent.keySet()) {
            String root = dsu.find(word);
            componentsMap.computeIfAbsent(root, x -> new TreeSet<>()).add(word);
        }

        List<String> result = new ArrayList<>();
        String[] words = text.split(" ");
        backtrack(0, words, componentsMap, dsu, new ArrayList<>(), result);
        Collections.sort(result);
        return result;
    }

    private static void backtrack(int idx, String[] words, Map<String, TreeSet<String>> map, 
                                 SynonymDSU dsu, List<String> current, List<String> result) {
        if (idx == words.length) {
            result.add(String.join(" ", current));
            return;
        }

        String word = words[idx];
        String root = dsu.parent.containsKey(word) ? dsu.find(word) : null;

        if (root == null) {
            // No synonyms exist for this word, move forward directly
            current.add(word);
            backtrack(idx + 1, words, map, dsu, current, result);
            current.remove(current.size() - 1);
        } else {
            // Enumerate all equivalent word choices in alphabetical order
            for (String synonym : map.get(root)) {
                current.add(synonym);
                backtrack(idx + 1, words, map, dsu, current, result);
                current.remove(current.size() - 1); // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> synonyms = Arrays.asList(Arrays.asList("happy", "joy"), Arrays.asList("sad", "sorrow"));
        System.out.println("Generated sentences matching vocabulary parameters:\n" + generateSentences(synonyms, "I am happy"));
        // [I am happy, I am joy]
    }
}