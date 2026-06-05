/**
 * PROBLEM: Process Restricted Friend Requests
 * * You are given an integer n denoting the number of users. You are also given a 2D array restrictions 
 * and an array requests. 
 * A friend request is approved if it does not violate any restriction. Return a boolean array indicating 
 * whether each friend request is approved.
 * * Strategy: Speculative Validation Filtering
 * For each request (u, v), find their potential future root connection profile. Check if this merge 
 * violates any restriction pair (r1, r2) by seeing if the root profiles match the restriction's roots.
 * If a clash is detected, block the request; if clear, approve the request and execute the union.
 */
public class RestrictedFriendRequests {
    static class GuardedDSU {
        int[] parent;
        public GuardedDSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP != rootQ) parent[rootP] = rootQ;
        }
    }

    public static boolean[] friendshipRequestProcessor(int n, int[][] restrictions, int[][] requests) {
        GuardedDSU dsu = new GuardedDSU(n);
        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0], v = requests[i][1];
            int rootU = dsu.find(u);
            int rootV = dsu.find(v);
            
            if (rootU == rootV) {
                result[i] = true; // Already indirectly connected, no new risk
                continue;
            }

            boolean holdsConstraint = true;
            // Check against all restrictions
            for (int[] restriction : restrictions) {
                int r1Root = dsu.find(restriction[0]);
                int r2Root = dsu.find(restriction[1]);
                
                // Speculative check: if u-v merged, r1 and r2 would share a root
                if ((rootU == r1Root && rootV == r2Root) || (rootU == r2Root && rootV == r1Root)) {
                    holdsConstraint = false;
                    break;
                }
            }

            if (holdsConstraint) {
                dsu.union(rootU, rootV);
                result[i] = true;
            } else {
                result[i] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] restrictions = {{0, 1}};
        int[][] requests = {{0, 2}, {1, 2}}; // 0-2 is valid, 1-2 is blocked because it transitively links 0 and 1
        System.out.println("Approval trace matrix outputs: " + 
            java.util.Arrays.toString(friendshipRequestProcessor(3, restrictions, requests))); // [true, false]
    }
}