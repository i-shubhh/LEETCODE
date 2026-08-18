import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {

        Map<Integer, Integer> count = new HashMap<>();

        int n = nums.length;

        // Check every subarray of size k
        for (int i = 0; i <= n - k; i++) {

            // Set prevents counting the same number
            // multiple times in one subarray
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            // This number appeared in one more subarray
            for (int x : seen) {
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
        }

        int answer = -1;

        // Find the largest number appearing
        // in exactly one subarray
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {

            if (entry.getValue() == 1) {
                answer = Math.max(answer, entry.getKey());
            }
        }

        return answer;
    }
}