import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map = new int[n + 1][m + 1];
        for (int[] p : puddles) {
            map[p[1]][p[0]] = -1;
        }
        
        map[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                int up = map[i - 1][j];
                int left = map[i][j - 1];
                if (up > 0) {
                    map[i][j] += up % 1000000007;
                }
                if (left > 0) {
                    map[i][j] += left % 1000000007;
                }
            }
        }
        
        
        return map[n][m] % 1000000007;
    }
}