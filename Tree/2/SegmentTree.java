public class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        // Insert leaf nodes in the second half of the tree array
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        // Build the tree by calculating parents
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int index, int val) {
        // Shift index to the leaf node
        index += n;
        tree[index] = val;
        
        // Bubble up and update all parents
        while (index > 0) {
            int left = index;
            int right = index;
            if (index % 2 == 0) {
                right = index + 1;
            } else {
                left = index - 1;
            }
            // Parent is updated
            tree[index / 2] = tree[left] + tree[right];
            index /= 2;
        }
    }

    public int sumRange(int left, int right) {
        // Shift indices to leaves
        left += n;
        right += n;
        int sum = 0;
        
        while (left <= right) {
            // If left is a right-child, add it and move right
            if ((left % 2) == 1) {
                sum += tree[left];
                left++;
            }
            // If right is a left-child, add it and move left
            if ((right % 2) == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        SegmentTree segTree = new SegmentTree(nums);
        
        System.out.println("Sum of range [0, 2]: " + segTree.sumRange(0, 2)); // 9
        segTree.update(1, 2); // nums array becomes [1, 2, 5]
        System.out.println("Sum of range [0, 2] after update: " + segTree.sumRange(0, 2)); // 8
    }
}