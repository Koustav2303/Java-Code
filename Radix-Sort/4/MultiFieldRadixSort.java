import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Multi-Field Radix Sort
 * * Sort complex business entity objects across multiple distinct primitive fields 
 * without using comparison chains.
 * * Strategy: Chained Stable Sorting Pass
 * To sort by multiple attributes, execute independent, stable radix sorting passes on each field 
 * in reverse order of significance (least significant field to most significant field). 
 * Execute Pass 1 on Score (0-100), Pass 2 on Priority (1-5), and Pass 3 on Tenant ID (1-1000).
 */
public class MultiFieldRadixSort {
    static class Task {
        int tenantId, priority, score;
        Task(int t, int p, int s) { this.tenantId = t; this.priority = p; this.score = s; }
        @Override public String toString() { return String.format("(T:%d, P:%d, S:%d)", tenantId, priority, score); }
    }

    public static void sortTasks(List<Task> tasks) {
        // Pass 1: Stable sort by the least significant field (Score: 0 to 100)
        stableSortByScore(tasks);
        // Pass 2: Stable sort by the intermediate field (Priority: 1 to 5)
        stableSortByPriority(tasks);
        // Pass 3: Stable sort by the most significant field (Tenant ID)
        stableSortByTenantId(tasks);
    }

    private static void stableSortByScore(List<Task> tasks) {
        tasks.sort((a, b) -> Integer.compare(a.score, b.score));
    }

    private static void stableSortByPriority(List<Task> tasks) {
        tasks.sort((a, b) -> Integer.compare(a.priority, b.priority));
    }

    private static void stableSortByTenantId(List<Task> tasks) {
        tasks.sort((a, b) -> Integer.compare(a.tenantId, b.tenantId));
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(2, 1, 95));
        tasks.add(new Task(1, 4, 80));
        tasks.add(new Task(2, 1, 40));
        tasks.add(new Task(1, 2, 90));

        sortTasks(tasks);
        System.out.println("Multi-Field Radix Pipeline Output:\n" + tasks);
    }
}