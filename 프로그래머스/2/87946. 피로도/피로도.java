import java.util.*;

class Solution {
    
    int k;
    int[][] dungeons;
    boolean[] visited;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        this.k = k;
        this.dungeons = dungeons;
        this.visited = new boolean[dungeons.length];
        
        dfs(0);
        return answer;
    }
    
    public void dfs(int cnt) {
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                k -= dungeons[i][1];
                
                dfs(cnt + 1);
                
                visited[i] = false;
                k += dungeons[i][1];
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}