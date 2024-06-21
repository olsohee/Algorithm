import java.util.*;

class Solution {
    
    public int solution(int alp, int cop, int[][] problems) {

        int goalAlp = 0;
        int goalCop = 0;
        
        for (int[] problem : problems) {
            goalAlp = Math.max(goalAlp, problem[0]);
            goalCop = Math.max(goalCop, problem[1]);
        }
        
        goalAlp = Math.max(goalAlp, alp);
        goalCop = Math.max(goalCop, cop);
        
        int[][] dp = new int[goalAlp + 1][goalCop + 1];
        
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                // 알고리즘 공부
                if (i + 1 > goalAlp) {
                    dp[goalAlp][j] = Math.min(dp[goalAlp][j], dp[i][j] + 1);
                } else {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                
                // 코딩 공부
                if (j + 1 > goalCop) {
                    dp[i][goalCop] = Math.min(dp[i][goalCop], dp[i][j] + 1);
                } else {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                // 문제 풀기
                for (int[] p : problems) {
                    if (i >= p[0] && j >= p[1]) {
                        int newAlp = i + p[2];
                        int newCop = j + p[3];
                        if (newAlp > goalAlp) newAlp = goalAlp;
                        if (newCop > goalCop) newCop = goalCop;
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + p[4]);
                    }
                }
            }
        }
        
        return dp[goalAlp][goalCop];
    }
}