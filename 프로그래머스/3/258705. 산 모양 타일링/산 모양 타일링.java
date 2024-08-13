import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        
        int mod = 10007;
        int[][] dp = new int[n + 1][2];
        
        if (tops[0] == 1) {
            dp[1][0] = 3;
            dp[1][1] = 1;
            
        } else {
            dp[1][0] = 2;
            dp[1][1] = 1;
        }
        
        for (int i = 2; i <= n; i++) {
            if (tops[i - 1] == 1) {
                dp[i][0] = ((dp[i - 1][0] + dp[i - 1][1]) * 2 + dp[i - 1][0]) % mod;
            } else {
                dp[i][0] = ((dp[i - 1][0] + dp[i - 1][1]) + dp[i - 1][0]) % mod;
            }
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
        }
        
        return (dp[n][0] + dp[n][1]) % mod;
    }
}