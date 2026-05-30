import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * PROBLEM: Reveal Cards In Increasing Order
 * * You are given an integer array deck. There is a deck of cards where every card has a unique integer.
 * You can order the deck in any order you want.
 * * You reveal the cards as follows:
 * 1. Take the top card of the deck, reveal it, and take it out of the deck.
 * 2. If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
 * 3. Repeat until all cards are revealed.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 * * Approach:
 * Sort the deck. Simulate the drawing process IN REVERSE using a Deque.
 * To reverse the process: Take the bottom card, put it on top, then add the next largest card to the top.
 */
public class RevealCards {
    public static int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> deque = new LinkedList<>();
        
        // Build the deck backwards
        for (int i = deck.length - 1; i >= 0; i--) {
            // Reverse of Step 2: Take from bottom, put on top
            if (!deque.isEmpty()) {
                deque.addFirst(deque.pollLast());
            }
            // Reverse of Step 1: Add the next card to the top
            deque.addFirst(deck[i]);
        }
        
        int[] result = new int[deck.length];
        int i = 0;
        for (int card : deque) {
            result[i++] = card;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] deck = {17, 13, 11, 2, 3, 5, 7};
        System.out.println("Correct initial deck order: " + Arrays.toString(deckRevealedIncreasing(deck))); 
        // [2, 13, 3, 11, 5, 17, 7]
    }
}