import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        if (alp > maxAlp) {
            maxAlp = alp;
        }
        
        if (cop > maxCop) {
            maxCop = cop;
        }
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 1. 알고력 +1
                int nextAlp = (i + 1) >= maxAlp ? maxAlp : i + 1;
                dp[nextAlp][j] = Math.min(dp[nextAlp][j], dp[i][j] + 1);
                
                // 2. 코딩력 +1
                int nextCop = (j + 1) >= maxCop ? maxCop : j + 1;
                dp[i][nextCop] = Math.min(dp[i][nextCop], dp[i][j] + 1);
                
                // 3. 풀 수 있는 문제 풀기
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) continue;
                    nextAlp = i + problem[2];
                    nextCop = j + problem[3];
                    if (nextAlp > maxAlp) nextAlp = maxAlp;
                    if (nextCop > maxCop) nextCop = maxCop;
                    
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                }
            }
        }
        
        // 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간
        return dp[maxAlp][maxCop];
    }
}