import java.util.*;

class Solution {
    
    int answer = Integer.MAX_VALUE;
    boolean[][] map;
    boolean[] visited;
    int n;
    
    public int solution(int n, int[][] wires) {
        
        this.n = n;
        
        for (int i = 0; i < wires.length; i++) {
            // 초기화
            map = new boolean[n + 1][n + 1];
            visited = new boolean[n + 1];
            
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                int n1 = wires[j][0];
                int n2 = wires[j][1];
                map[n1][n2] = true;
                map[n2][n1] = true;
            }
            
            // dfs
            int result1 = dfs(1);
            int result2 = n - result1;
            
            answer = Math.min(answer, Math.abs(result1 - result2));
        }
        
        // 개수 차이의 절대값
        return answer;
    }
    
    // 연결된 한 덩어리의 노드 개수 반환
    public int dfs (int start) {
        
        visited[start] = true;
        int sum = 1;
        
        for (int i = 1; i <= n; i++) {
            if (map[start][i] && !visited[i]) {
                sum += dfs(i);
            }
        }
        return sum;
    }
}