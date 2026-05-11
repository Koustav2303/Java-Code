public class Box {
    int l, b, h;

    Box() { this(1, 1, 1); } // Calls the 3-arg constructor
    Box(int l, int b, int h) {
        this.l = l; this.b = b; this.h = h;
    }

    public static void main(String[] args) {
        Box b = new Box();
        System.out.println("Default Volume: " + (b.l * b.b * b.h));
    }
}