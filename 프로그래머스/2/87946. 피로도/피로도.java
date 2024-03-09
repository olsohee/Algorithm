import java.util.*;

class Solution {
    
    int answer = -1; // 탐험할 수 있는 최대 던전의 수
    boolean[] visited;
    int k; 
    int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        visited = new boolean[dungeons.length];
        
        dfs(0, 0);
        
        return answer;
    }
    
    public void dfs(int start, int cnt) {
        if (cnt == dungeons.length) {
            answer = dungeons.length;
            return;
        }
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                if (k >= dungeons[i][0]) {
                    visited[i] = true;
                    k -= dungeons[i][1];
                    dfs(i, cnt + 1);
                    
                    // 초기화
                    visited[i] = false;
                    k += dungeons[i][1];
                } else {
                    answer = Math.max(answer, cnt);
                } 
            }
        }
    }
}