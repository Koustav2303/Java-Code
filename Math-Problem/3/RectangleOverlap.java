public class RectangleOverlap {
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // rec[0] = x1 (left), rec[1] = y1 (bottom), rec[2] = x2 (right), rec[3] = y2 (top)
        
        // Check if one rectangle is completely to the side of the other
        boolean isLeftOrRight = rec1[2] <= rec2[0] || rec1[0] >= rec2[2];
        boolean isTopOrBottom = rec1[3] <= rec2[1] || rec1[1] >= rec2[3];
        
        // If it's NOT to the side, and NOT above/below, they must overlap
        return !(isLeftOrRight || isTopOrBottom);
    }

    public static void main(String[] args) {
        int[] rec1 = {0, 0, 2, 2};
        int[] rec2 = {1, 1, 3, 3};
        System.out.println("Do the rectangles overlap? " + isRectangleOverlap(rec1, rec2));
    }
}