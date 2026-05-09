public class StringMirror {
    public static void main(String[] args) {
        String original = "DataStructures";
        String reversed = new StringBuilder(original).reverse().toString();
        System.out.println(reversed);
    }
}