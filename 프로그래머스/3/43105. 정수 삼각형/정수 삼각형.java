import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        
        int y = triangle.length;
        int x = triangle[triangle.length - 1].length;
        
        int[][] arr = new int[y + 1][x + 1];
        int[][] dp = new int[y + 1][x + 1];
        
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                arr[i + 1][j + 1] = triangle[i][j];
            }
        }
        
        dp[1][1] = arr[1][1];
        for (int i = 2; i < y + 1; i++) {
            for (int j = 1; j < x + 1; j++) {
                // (위에 dp OR 왼쪽 위 dp) + 현재 자리 arr
                int max = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + arr[i][j];
                dp[i][j] = Math.max(dp[i][j], max);
            }
        }
        
        // 마지막 줄에서 최댓값 찾기
        int answer = 0;
        for (int i = 1; i < x; i++) {
            answer = Math.max(answer, dp[y][i]);
        }
        
        return answer;
    }
}