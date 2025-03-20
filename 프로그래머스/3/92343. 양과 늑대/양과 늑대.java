import java.util.*;

class Solution {
    
    int answer = 0; // 양의 최대 수
    int[] info;
    List<List<Integer>> list = new ArrayList<>();
    boolean[] visited;
    int n;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        n = info.length;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
        }
        
        // 0번 노드를 시작으로 dfs 시작
        visited[0] = true;
        dfs(0, 1, 0);
        
        return answer; 
    }
    
    private void dfs(int wolf, int sheep, int now) {
        
        // 잡아 먹힘
        if (wolf >= sheep) {
            return;
        }
        
        answer = Math.max(answer, sheep);
        
        // 다음에 이동할 노드 = 방문처리한 곳에서 이어지는 노드 && 방문하지 않은 노드
        for (int i = 0; i < n; i++) {
            if (!visited[i]) continue;
            for (int next : list.get(i)) {
                if (!visited[next]) {
                    // 이동
                    visited[next] = true;
                    if (info[next] == 0) { // 양
                        dfs(wolf, sheep + 1, next);
                    } 
                    if (info[next] == 1) { // 늑대
                        dfs(wolf + 1, sheep, next);
                    }
                    visited[next] = false;
                }
            }
        }
    }
}