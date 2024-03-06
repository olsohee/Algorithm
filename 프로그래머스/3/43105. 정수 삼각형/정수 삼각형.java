import java.util.*;

class Solution {
 
    public int solution(int[][] triangle) {
            int n = triangle.length;
            int[][] arr = new int[n][n];
            int[][] dp = new int[n][n];
            for (int i = 0; i < triangle.length; i++) {
                arr[i] = triangle[i];
            }
//            for (int[] ints : arr) {
//                for (int anInt : ints) {
//                    System.out.print(anInt + " ");
//                }
//                System.out.println();
//            }

            // dp 초기화
            dp[0][0] = arr[0][0];
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        dp[i][j] = arr[i][j] + dp[i - 1][j];
                        continue;
                    }
                    dp[i][j] = arr[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }

            // 최댓값 찾기
            int answer = 0;
            for (int i = 0; i < n; i++) {
                answer = Math.max(answer, dp[n - 1][i]);
            }
            return answer;
        }
}