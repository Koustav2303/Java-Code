import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        int[] lastIndices = new int[26];
        
        // Greedily record the absolute LAST time each character appears
        for (int i = 0; i < s.length(); i++) {
            lastIndices[s.charAt(i) - 'a'] = i;
        }
        
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // The current partition must extend to at least the last occurrence of this character
            end = Math.max(end, lastIndices[s.charAt(i) - 'a']);
            
            // If we have reached the end of the required partition, cut it
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println("Partition sizes: " + partitionLabels(s));
        // Output: [9, 7, 8]
    }
}