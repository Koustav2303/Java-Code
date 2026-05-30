import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Pascal's Triangle
 * * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * * Example:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * * Approach:
 * Simulate the row-by-row creation. Each row starts and ends with 1.
 * For the inner elements, add the two elements from the previous row.
 */
public class PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) return triangle;

        // Base case: first row is always [1]
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(i - 1);

            // First element is always 1
            row.add(1);

            // Simulate the addition of the two numbers above
            for (int j = 1; j < i; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // Last element is always 1
            row.add(1);
            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        System.out.println("Pascal's Triangle (5 rows): " + generate(5));
    }
}