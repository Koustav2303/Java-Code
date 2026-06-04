import java.util.*;

/**
 * PROBLEM: The Earliest Moment When Everyone Become Friends
 * * There are n people in a social group labeled from 0 to n - 1. You are given an array logs 
 * where logs[i] = [timestamp, id1, id2] indicates that id1 and id2 became friends at timestamp.
 * Return the earliest timestamp for which every person became friends with each other. 
 * If there is no such earliest moment, return -1.
 * * Strategy: Chronological Event Processing Pipeline
 * Sort the log entries in ascending order by timestamp. Process friendships sequentially using a component 
 * tracker. When the isolated group count drops to 1, return the current log's timestamp.
 */
public class EarliestMomentFriends {
    static class TrackerDSU {
        int[] parent;
        int components;

        public TrackerDSU(int n) {
            parent = new int[n];
            components = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                components--;
                return true;
            }
            return false;
        }
    }

    public static int earliestAcq(int[][] logs, int n) {
        // Sort chronologically by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));
        TrackerDSU dsu = new TrackerDSU(n);

        for (int[] log : logs) {
            if (dsu.union(log[1], log[2])) {
                if (dsu.components == 1) {
                    return log[0]; // Global network completely unified
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] logs = {
            {20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3},
            {20190211, 1, 5}, {20190224, 2, 4}, {20190301, 0, 3}
        };
        System.out.println("Earliest moment unified network reached: " + earliestAcq(logs, 6)); // 20190301
    }
}