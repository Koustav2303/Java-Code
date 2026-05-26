public class StraightLineCheck {
    public static boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) return true;
        
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int x1 = coordinates[1][0], y1 = coordinates[1][1];
        
        // Slope difference: dy1 / dx1 == dy2 / dx2
        // Cross multiplied: dy1 * dx2 == dy2 * dx1
        int dx1 = x1 - x0;
        int dy1 = y1 - y0;
        
        for (int i = 2; i < coordinates.length; i++) {
            int x2 = coordinates[i][0], y2 = coordinates[i][1];
            
            int dx2 = x2 - x1;
            int dy2 = y2 - y1;
            
            if (dy1 * dx2 != dy2 * dx1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}};
        System.out.println("Are the points a straight line? " + checkStraightLine(points));
    }
}