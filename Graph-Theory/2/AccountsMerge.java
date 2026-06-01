import java.util.*;

/**
 * PROBLEM: Accounts Merge
 * * Given a list of accounts where each element accounts[i] is a list of strings, where the first element 
 * accounts[i][0] is a name, and the rest of the elements are emails owned by that name.
 * Merge these accounts if they share an overlapping email. 
 * * Strategy:
 * Advanced application of Union-Find (Disjoint Set). 
 * Map every unique email to a unique integer ID, and track an overall map of Email -> Name.
 * Group shared email component sets via Union operations, then sort and cluster them into lists.
 * * Complexity:
 * Time Complexity: O(N log N) due to sorting emails for the output.
 * Space Complexity: O(N) map and parent arrays.
 */
public class AccountsMerge {
    static class DSU {
        int[] parent;
        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(10001); // Safe bounded index for email mapping IDs
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToID = new HashMap<>();
        int id = 0;
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }
                // Union the current email to the first email of the sub-account
                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }
        
        // Group emails together by their disjoint set parent roots
        Map<Integer, List<String>> groups = new HashMap<>();
        for (String email : emailToID.keySet()) {
            int root = dsu.find(emailToID.get(email));
            groups.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
        }
        
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> componentEmails : groups.values()) {
            Collections.sort(componentEmails); // Required sorting condition
            List<String> account = new ArrayList<>();
            account.add(emailToName.get(componentEmails.get(0))); // Add Name header
            account.addAll(componentEmails);
            mergedAccounts.add(account);
        }
        return mergedAccounts;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        
        System.out.println("Merged Accounts:\n" + accountsMerge(accounts));
    }
}