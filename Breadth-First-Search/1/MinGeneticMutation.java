import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinGeneticMutation {
    public static int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> dict = new HashSet<>(Arrays.asList(bank));
        if (!dict.contains(endGene)) return -1;
        
        char[] dna = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        
        int mutations = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endGene)) return mutations;
                
                char[] chars = curr.toCharArray();
                for (int j = 0; j < 8; j++) {
                    char original = chars[j];
                    for (char c : dna) {
                        if (c == original) continue;
                        chars[j] = c;
                        String mutant = new String(chars);
                        
                        if (dict.contains(mutant)) {
                            queue.add(mutant);
                            dict.remove(mutant); // Mark as visited
                        }
                    }
                    chars[j] = original; // Backtrack for next loop
                }
            }
            mutations++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String start = "AACCGGTT", end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println("Min mutations: " + minMutation(start, end, bank)); // 2
    }
}