import java.util.*;

class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }
        
        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0; // dp[i - 1][0~4]에서 최댓값 찾기
                for (int k = 0; k < 4; k++) {
                    if (k == j) continue;
                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }
        
        // for (int i = 0; i < dp.length; i++) {
        //     for (int j = 0; j < 4; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }
        
        return answer;
    }
}