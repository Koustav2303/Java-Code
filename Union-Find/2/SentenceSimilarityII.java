import java.util.*;

/**
 * PROBLEM: Sentence Similarity II
 * * Given two sentences words1 and words2 (represented as string arrays), and a list of similar 
 * word pairs pairs, determine if words1 and words2 are similar.
 * The similarity relation is transitive. If 'great' and 'fine' are similar, and 'fine' and 
 * 'good' are similar, then 'great' and 'good' are similar.
 * * Strategy: String-Based DSU Map
 * Use a HashMap to map strings to their parents. If a word isn't mapped, it is its own parent.
 * Union all pairs together. Then iterate through the sentences and check if words at matching 
 * positions share the same component root.
 */
public class SentenceSimilarityII {
    static class StringDSU {
        Map<String, String> parent = new HashMap<>();

        public String find(String s) {
            if (!parent.containsKey(s)) {
                parent.put(s, s);
            }
            if (parent.get(s).equals(s)) return s;
            parent.put(s, find(parent.get(s))); // Path compression
            return parent.get(s);
        }

        public void union(String s1, String s2) {
            String root1 = find(s1);
            String root2 = find(s2);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }

    public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        
        StringDSU dsu = new StringDSU();
        for (List<String> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }

        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) continue;
            if (!dsu.find(w1).equals(dsu.find(w2))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] w1 = {"great", "acting", "skills"};
        String[] w2 = {"fine", "drama", "talent"};
        List<List<String>> pairs = Arrays.asList(
            Arrays.asList("great", "fine"),
            Arrays.asList("fine", "good"),
            Arrays.asList("acting", "drama"),
            Arrays.asList("skills", "talent")
        );
        System.out.println("Are sentences similar? " + areSentencesSimilarTwo(w1, w2, pairs)); // true
    }
}