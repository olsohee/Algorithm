import java.util.*;

class Solution {
    
    int n;
    boolean[][] map;
    boolean[] visited;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        
        for (int i = 0; i < wires.length; i++) {
            map = new boolean[n + 1][n + 1];
            visited = new boolean[n + 1];
            
            // 1개의 선 끊은 map 채우기
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                int n1 = wires[j][0];
                int n2 = wires[j][1];
                map[n1][n2] = map[n2][n1] = true;
            }
            
            // 1번 노드를 기준으로 dfs
            visited[1] = true;
            int cnt = dfs(1);
            
            // 차이
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    private int dfs(int start) {
        int sum = 1;
        
        for (int i = 1; i <= n; i++) {
            if (map[start][i]) {
                if (visited[i]) continue;
                visited[i] = true;
                sum += dfs(i);
                visited[i] = false;
            }
        }
        return sum;
    }
}