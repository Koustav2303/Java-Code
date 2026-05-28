import java.util.Arrays;

/**
 * PROBLEM: Merge Sorted Array
 * * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * * The final sorted array should not be returned by the function, but instead be stored inside 
 * the array nums1. To accommodate this, nums1 has a length of m + n.
 * * Example:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * * Approach:
 * Use three pointers starting from the *end* of the arrays to avoid overwriting elements in nums1.
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // Pointer for valid elements in nums1
        int p2 = n - 1; // Pointer for nums2
        int p = m + n - 1; // Pointer for the end of nums1 (where to write)
        
        // While there are still elements to process in nums2
        while (p2 >= 0) {
            // If nums1 has valid elements left and the current nums1 element is larger
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]
    }
}