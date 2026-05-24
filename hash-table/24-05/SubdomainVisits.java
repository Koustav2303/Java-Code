import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubdomainVisits {
    public static List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> counts = new HashMap<>();

        for (String domainInfo : cpdomains) {
            String[] parts = domainInfo.split(" ");
            int count = Integer.parseInt(parts[0]);
            String domain = parts[1];

            // Add the full domain
            counts.put(domain, counts.getOrDefault(domain, 0) + count);

            // Parse and add the parent domains
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    String sub = domain.substring(i + 1);
                    counts.put(sub, counts.getOrDefault(sub, 0) + count);
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (String key : counts.keySet()) {
            result.add(counts.get(key) + " " + key);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] domains = {"9001 discuss.leetcode.com"};
        System.out.println("Input: " + Arrays.toString(domains));
        
        List<String> results = subdomainVisits(domains);
        System.out.println("Visit Counts:");
        for (String res : results) System.out.println("  " + res);
    }
}