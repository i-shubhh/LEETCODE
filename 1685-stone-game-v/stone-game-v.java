class Solution {

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score Alice can get
        // from stones l to r
        int[][] dp = new int[n][n];

        // Length of subarray
        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len - 1 < n; l++) {

                int r = l + len - 1;

                // Try every possible split
                for (int k = l; k < r; k++) {

                    // Sum of left part
                    int left = prefix[k + 1] - prefix[l];

                    // Sum of right part
                    int right = prefix[r + 1] - prefix[k + 1];

                    if (left < right) {

                        // Bob throws right part
                        // Alice keeps left part
                        dp[l][r] = Math.max(
                            dp[l][r],
                            left + dp[l][k]
                        );

                    } else if (left > right) {

                        // Bob throws left part
                        // Alice keeps right part
                        dp[l][r] = Math.max(
                            dp[l][r],
                            right + dp[k + 1][r]
                        );

                    } else {

                        // Both sides have equal value.
                        // Alice chooses the better side.
                        dp[l][r] = Math.max(
                            dp[l][r],
                            left + Math.max(
                                dp[l][k],
                                dp[k + 1][r]
                            )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}