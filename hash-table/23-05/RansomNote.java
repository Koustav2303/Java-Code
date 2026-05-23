import java.util.HashMap;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> letterCounts = new HashMap<>();

        // Log all available letters from the magazine
        for (char c : magazine.toCharArray()) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }

        // Check if we have enough letters for the ransom note
        for (char c : ransomNote.toCharArray()) {
            if (!letterCounts.containsKey(c) || letterCounts.get(c) == 0) {
                return false; // Missing a letter or ran out
            }
            letterCounts.put(c, letterCounts.get(c) - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        String note = "aa";
        String magazine = "aab";
        System.out.println("Note: " + note + " | Magazine: " + magazine);
        System.out.println("Can construct? " + canConstruct(note, magazine));
    }
}