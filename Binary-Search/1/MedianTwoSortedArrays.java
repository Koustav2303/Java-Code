public class MedianTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to minimize the binary search space
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        while (low <= high) {
            int partitionX = low + (high - low) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // Correct partition found
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If total length is even
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } 
                // If total length is odd
                else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } 
            // We are too far right in nums1, move left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // We are too far left in nums1, move right
            else {
                low = partitionX + 1;
            }
        }
        throw new IllegalArgumentException("Arrays are not sorted");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median is: " + findMedianSortedArrays(nums1, nums2)); // 2.0
    }
}