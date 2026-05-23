import java.util.HashMap;

public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> mapS2T = new HashMap<>();
        HashMap<Character, Character> mapT2S = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if mapping from S -> T is broken
            if (mapS2T.containsKey(charS) && mapS2T.get(charS) != charT) return false;
            
            // Check if mapping from T -> S is broken
            if (mapT2S.containsKey(charT) && mapT2S.get(charT) != charS) return false;

            mapS2T.put(charS, charT);
            mapT2S.put(charT, charS);
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        System.out.println("Are '" + s + "' and '" + t + "' isomorphic? " + isIsomorphic(s, t));
        
        String s2 = "foo";
        String t2 = "bar";
        System.out.println("Are '" + s2 + "' and '" + t2 + "' isomorphic? " + isIsomorphic(s2, t2));
    }
}