import java.util.*;

class Solution {
    
    int n;
    int[][][] dp;
    int[] nums;
    char[] operations;
    
    public int solution(String arr[]) {
        
        n = arr.length / 2;
        dp = new int[2][n + 1][n + 1];
        nums = new int[n + 1];
        operations = new char[n];
        
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Integer.parseInt(arr[i]);
            } else {
                operations[i / 2] = arr[i].charAt(0);
            }
        }
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }
        
        return solve(0, 0, n);
    }
    
    public int solve(int flag, int start, int end) {
        
        if (start == end) {
            return dp[flag][start][end] = nums[start];
        }
        
        if (flag == 0 && dp[0][start][end] != Integer.MIN_VALUE) {
            return dp[0][start][end];
        }
        
        if (flag == 1 && dp[1][start][end] != Integer.MAX_VALUE) {
            return dp[1][start][end];
        }
        
        int result = (flag == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        // 최댓값 구하기
        if (flag == 0) {
            for (int mid = start; mid < end; mid++) {
                // 최대 - 최소 
                if (operations[mid] == '-') {
                    result = Math.max(result, solve(0, start, mid) - solve(1, mid + 1, end));
                } 
                // 최대 + 최대
                if (operations[mid] == '+') {
                    result = Math.max(result, solve(0, start, mid) + solve(0, mid + 1, end));
                }
            }
        }
        
        // 최솟값 구하기
        if (flag == 1) {
            for (int mid = start; mid < end; mid++) {
                // 최소 - 최대 
                if (operations[mid] == '-') {
                    result = Math.min(result, solve(1, start, mid) - solve(0, mid + 1, end));
                } 
                // 최소 + 최소
                if (operations[mid] == '+') {
                    result = Math.min(result, solve(1, start, mid) + solve(1, mid + 1, end));
                }
            }
        }
        
        return dp[flag][start][end] = result;
    }
}