/**
 * PROBLEM: Find the Winner of the Circular Game
 * * There are n friends that are playing a game. The friends are sitting in a circle and are 
 * numbered from 1 to n in clockwise order.
 * Start at the 1st friend. Count the next k friends in the clockwise direction. The kth friend 
 * leaves the circle and loses the game. Repeat until 1 friend remains.
 * * Approach:
 * This can be simulated perfectly using a Queue.
 * Dequeue and enqueue the first `k - 1` elements (sending them to the back of the line).
 * The `k`th element is dequeued and discarded permanently. Repeat.
 */

import java.util.LinkedList;
import java.util.Queue;

public class CircularGame {
    public static int findTheWinner(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        
        while (queue.size() > 1) {
            // Rotate the circle k-1 times
            for (int i = 0; i < k - 1; i++) {
                queue.add(queue.poll()); // Move to the back
            }
            // Eliminate the kth person
            queue.poll();
        }
        
        return queue.peek(); // The winner
    }

    public static void main(String[] args) {
        int n = 5, k = 2;
        System.out.println("The winner is: " + findTheWinner(n, k)); // 3
    }
}