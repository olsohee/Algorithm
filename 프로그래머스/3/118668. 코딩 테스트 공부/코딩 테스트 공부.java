import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        
        int maxAlp = 0; // 최대 알고력
        int maxCop = 0; // 최대 코딩력
        
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        if (alp > maxAlp) {
            alp = maxAlp;
        }
        
        if (cop > maxCop) {
            cop = maxCop;
        }
        
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 알고리즘 공부
                int nextAlp = i + 1 > maxAlp ? maxAlp : i + 1;
                dp[nextAlp][j] = Math.min(dp[nextAlp][j], dp[i][j] + 1);
                    
                // 코딩 공부
                int nextCop = j + 1 > maxCop ? maxCop : j + 1;
                dp[i][nextCop] = Math.min(dp[i][nextCop], dp[i][j] + 1);
                    
                // 문제 풀기
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1]) continue;
                    nextAlp = i + problem[2];
                    nextCop = j + problem[3];
                    if (nextAlp > maxAlp) {
                        nextAlp = maxAlp;
                    }
                    if (nextCop > maxCop) {
                        nextCop = maxCop;
                    }
                    
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                }
            }
        }
        
        // for (int i = 10; i < dp.length; i++) {
        //     for (int j = 10; j < dp[i].length; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        
        return dp[maxAlp][maxCop];
    }
}