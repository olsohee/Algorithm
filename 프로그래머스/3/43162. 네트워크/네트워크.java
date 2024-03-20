import java.util.*;

class Solution {
    
    int n;
    int[][] computers;
    boolean[] visited;
    int answer = 0;
    
    public int solution(int n, int[][] computers) {
        
        this.n = n;
        this.computers = computers;
        this.visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && !visited[j]) {
                    dfs(j);
                    answer++;
                }
            }
        }
        
        // 네트워크 개수
        return answer;
    }
    
    public void dfs(int start) {
        
        visited[start] = true;
        
        for (int i = 0; i < n; i++) {
            if (computers[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}