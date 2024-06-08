class Solution {
    
    int n;
    int[] nums;
    char[] opers;
    int[][][] dp;
    
    public int solution(String arr[]) {
        // 초기화
        n = arr.length / 2;
        nums = new int[n + 1];
        opers = new char[n];
        
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                nums[i / 2] = Integer.parseInt(arr[i]);
            } else {
                opers[i / 2] = arr[i].charAt(0);
            }
        }
        
        dp = new int[2][n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }
        
        return solve(0, 0, n); // nums의 인덱스 0 ~ n까지 숫자들의 연산 최댓값
    }
    
    public int solve (int flag, int start, int end) {
        if (start == end) {
            dp[flag][start][end] = nums[start];
        }
        
        if (flag == 0 && dp[flag][start][end] != Integer.MIN_VALUE) {
            return dp[flag][start][end];
        }
        if (flag == 1 && dp[flag][start][end] != Integer.MAX_VALUE) {
            return dp[flag][start][end];
        }
        
        int result = flag == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        
        // 최댓값을 구해야 하는 경우
        if (flag == 0) {
            for (int i = start; i < end; i++) {
                // 최대 - 최소
                if (opers[i] == '-') {
                     result = Math.max(result, solve(0, start, i) - solve(1, i + 1, end));
                } 
                // 최대 + 최대
                else {
                    result = Math.max(result, solve(0, start, i) + solve(0, i + 1, end));
                }
            }
        } 
        
        // 최솟값을 구해야 하는 경우
        else {
            for (int i = start; i < end; i++) {
                // 최소 - 최대
                if (opers[i] == '-') {
                     result = Math.min(result, solve(1, start, i) - solve(0, i + 1, end));
                } 
                // 최소 + 최소
                else {
                    result = Math.min(result, solve(1, start, i) + solve(1, i + 1, end));
                }
            }
        }
            
        return dp[flag][start][end] = result;
    }
}