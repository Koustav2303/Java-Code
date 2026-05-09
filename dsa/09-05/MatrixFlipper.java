public class MatrixFlipper {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {3, 4}};
        int[][] transpose = new int[2][2];
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) transpose[i][j] = matrix[j][i];
        }
        System.out.println("Transposed [0][1]: " + transpose[0][1]);
    }
}