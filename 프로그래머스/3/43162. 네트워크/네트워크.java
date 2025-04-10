import java.util.*;

class Solution {
    
    int n;
    int[][] computers;
    boolean[] visited;
    int answer;
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
                answer++;
            }
        }
        return answer;
    }
    
    private void dfs(int start) {
        for (int i = 0; i < n; i++) {
            if (computers[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}