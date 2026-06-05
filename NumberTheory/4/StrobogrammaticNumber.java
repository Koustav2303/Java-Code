/**
 * PROBLEM: Strobogrammatic Number
 * * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Given a string num representing a number, determine if it is strobogrammatic.
 * * Strategy: Symmetric Mirror Pointer Array
 * Valid reversible character pairs are: `(0,0)`, `(1,1)`, `(8,8)`, `(6,9)`, and `(9,6)`. 
 * Initialize a two-pointer loop checking outward characters inward. If a pair fails to map 
 * to a valid rotational reflection, reject the number.
 */
public class StrobogrammaticNumber {
    public static boolean isStrobogrammatic(String num) {
        int left = 0;
        int right = num.length() - 1;

        while (left <= left) { // Standard mirror validation loop
            if (left > right) break;
            
            char c1 = num.charAt(left);
            char c2 = num.charAt(right);

            // Verify if the character pair forms a valid strobogrammatic reflection
            if (c1 == '0' && c2 == '0') { }
            else if (c1 == '1' && c2 == '1') { }
            else if (c1 == '8' && c2 == '8') { }
            else if (c1 == '6' && c2 == '9') { }
            else if (c1 == '9' && c2 == '6') { }
            else {
                return false; // Found an asymmetric character mapping mutation
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is '69' strobogrammatic? " + isStrobogrammatic("69"));   // true
        System.out.println("Is '818' strobogrammatic? " + isStrobogrammatic("818")); // true
        System.out.println("Is '25' strobogrammatic? " + isStrobogrammatic("25"));   // false
    }
}