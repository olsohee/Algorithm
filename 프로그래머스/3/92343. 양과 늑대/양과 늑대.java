import java.util.*;

class Solution {
    
    int[] info;
    int[][] edges;
    boolean[] visited;
    List<List<Integer>> routeList = new ArrayList<>();
    int answer;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        visited = new boolean[info.length];
        
        for (int i = 0; i < info.length; i++) {
            routeList.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            routeList.get(edge[0]).add(edge[1]);
        }
        
        dfs(0, 0, 0);
        return answer;
    }
    
    private void dfs(int now, int sheep, int wolf) {
        if (info[now] == 0) sheep++;
        if (info[now] == 1) wolf++;
        visited[now] = true; // 첫 방문(=양/늑대 수집) 체크
        
        if (sheep <= wolf) return;
        answer = Math.max(answer, sheep);
        
        // 현재 노드에서 다음 노드로 이동
        for (int i = 0; i < info.length; i++) {
            if (visited[i]) {
                // i번 노드의 자식 노드로 이동
                for (int next : routeList.get(i)) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    dfs(next, sheep, wolf);
                    visited[next] = false;
                }
            }
        }
    }
}