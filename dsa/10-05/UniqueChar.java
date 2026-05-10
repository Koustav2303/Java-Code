public class UniqueChar {
    public static void main(String[] args) {
        String s = "leetcode";
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                System.out.println("First unique char index: " + i);
                return;
            }
        }
    }
}