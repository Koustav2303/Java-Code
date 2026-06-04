import java.util.*;

/**
 * PROBLEM: Find All People With Secret
 * * You are given an integer n denoting there are n people numbered from 0 to n - 1. You are also given a 0-indexed 
 * 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei.
 * Person 0 has a secret and initially shares it with person firstPerson at time 0.
 * Return a list of all the people who have the secret after all the meetings have taken place.
 * * Strategy: Multi-Stage Window Reset Isolation
 * Sort meetings chronologically. Group meetings that occur at the exact same timestamp. 
 * Process all meetings in the current time window by unioning the attendees. 
 * Then, inspect the participants: if a person is not connected to the root secret holder (Person 0), 
 * disconnect them by resetting their parent pointer back to themselves before moving to the next time window.
 */
public class FindAllPeopleWithSecret {
    static class ResetDSU {
        int[] parent;
        public ResetDSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                // Keep the secret holder (absolute parent root 0) at the base of the chain
                if (rootP == 0) parent[rootQ] = 0;
                else parent[rootP] = rootQ;
            }
        }
        public void reset(int i) {
            parent[i] = i; // Sever historical temporary node links
        }
    }

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));
        ResetDSU dsu = new ResetDSU(n);
        dsu.union(0, firstPerson); // Base initial infection configuration tracking step

        int i = 0, m = meetings.length;
        while (i < m) {
            int currentTime = meetings[i][2];
            List<Integer> pool = new ArrayList<>();
            
            // Collect all meetings happening at the exact same timestamp
            while (i < m && meetings[i][2] == currentTime) {
                dsu.union(meetings[i][0], meetings[i][1]);
                pool.add(meetings[i][0]);
                pool.add(meetings[i][1]);
                i++;
            }

            // Disconnect individuals who didn't cross paths with a secret holder during this window
            for (int person : pool) {
                if (dsu.find(person) != dsu.find(0)) {
                    dsu.reset(person);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (dsu.find(p) == dsu.find(0)) ans.add(p);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] meetings = {{3, 1, 3}, {1, 2, 2}, {0, 3, 3}};
        System.out.println("People possessing secret knowledge: " + findAllPeople(6, meetings, 1)); // [0, 1, 3]
    }
}