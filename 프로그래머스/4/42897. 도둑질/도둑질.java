import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        int[][] dp = new int[2][n];
        
        dp[0][0] = dp[0][1] = money[0];
        dp[1][1] = money[1];
        
        for (int i = 2; i < n; i++) {
            if (i == n - 1) {
                dp[0][i] = dp[0][i - 1];
                dp[1][i] = Math.max(dp[1][i - 1], dp[1][i - 2] + money[i]);
                continue;
            }
            
            dp[0][i] = Math.max(dp[0][i - 1], dp[0][i - 2] + money[i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[1][i - 2] + money[i]);
        }
        
        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}