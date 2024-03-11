import java.util.*;

class Solution {
    
    boolean[][] map;
    boolean[] visited;
    int n;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        
        map = new boolean[n][n];
        visited = new boolean[n];
        this.n = n;
        
        for (int i = 0; i < n - 1; i++) {
            // 초기화
            for (boolean[] arr : map) {
                Arrays.fill(arr, false);
            }
            Arrays.fill(visited, false);
            
            // map 채우기
            for (int j = 0; j < n - 1; j++) {
                if (i == j) continue;
                int num1 = wires[j][0] - 1;
                int num2 = wires[j][1] - 1;
                map[num1][num2] = true;
                map[num2][num1] = true;
            }
            
            // map을 기반으로 dfs
            int cnt = dfs(0);
            answer = Math.min(answer, Math.abs(cnt - (n - cnt)));
        }
        return answer;
    }
    
    public int dfs(int start) {
        
        visited[start] = true;
        int sum = 1;
        
        for (int i = 0; i < n; i++) {
            if (map[start][i] && !visited[i]) {
                sum += dfs(i);
            }
        }
        
        return sum;
    }
}