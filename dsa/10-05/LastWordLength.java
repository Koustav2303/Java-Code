public class LastWordLength {
    public static void main(String[] args) {
        String s = "Hello World DSA";
        s = s.trim();
        int lastSpace = s.lastIndexOf(' ');
        System.out.println("Length: " + (s.length() - lastSpace - 1));
    }
}