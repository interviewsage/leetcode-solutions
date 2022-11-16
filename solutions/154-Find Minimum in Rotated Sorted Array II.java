// LeetCode Question URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/1529323/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Optimal-Binary-Search-w-Early-Exit-and-FollowUp

/**
 * Very similar to Part 1 of this question. Only difference is to check if
 * start, end and mid all are same.
 *
 * <pre>
 * Time Complexity:
 * Worst Case: O(N/2). If all nums are same.
 * This will reduce to log(N) in there are no duplicates
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end && nums[start] >= nums[end]) {
            int mid = start + (end - start) / 2;
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                while (start < end && nums[start] == nums[end]) {
                    start++;
                    end--;
                }
            } else if (nums[start] <= nums[mid]) {
                start = mid + 1;
            } else {
                end = mid;
                start++;
            }
        }

        return nums[start];
    }
}

class Solution2 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 1 || nums[0] < nums[len - 1]) {
            return nums[0];
        }
        if (len == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int start = 0;
        int end = len - 1;

        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = start + (end - start) / 2;

            while (start < mid && nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
            }
            if (nums[mid] <= nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return nums[start];
    }
}

/**
 * Follow-Up: Return the minimum value at the correct minimum value index
 *
 * Refer:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/48808/My-pretty-simple-code-to-solve-it/48840
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution3 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end && nums[start] >= nums[end]) {
            int mid = start + (end - start) / 2;

            if (nums[start] < nums[mid]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                // nums[start] >= nums[mid] && nums[mid] >= nums[end]
                // 5 ... 4 ... 4
                // 5 ... 5 ... 5
                // 5 ... 5 ... 4
                if (nums[end - 1] > nums[end]) {
                    return nums[end];
                }
                end--; // End cannot be pivot anymore
            }
        }

        return nums[start];
    }
}
