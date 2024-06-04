import java.util.*;

class Solution {
    
    static final int mod = 10007;
    
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = tops[0] == 1 ? 3 : 2;
        
        for (int i = 1; i < n; i++) {
            int n1 = tops[i] == 1 ? 2 : 1;
            int n2 = tops[i] == 1 ? 3 : 2;
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][1] = (dp[i - 1][0] * n1 + dp[i - 1][1] * n2) % mod;
        }
        
        return (dp[n - 1][0] + dp[n - 1][1])  % mod;
    }
}