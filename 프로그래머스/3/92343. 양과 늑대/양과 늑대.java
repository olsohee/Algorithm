import java.util.*;

class Solution {
    
    int n;
    int[] info;
    int[][] edges;
    List<List<Integer>> routeList = new ArrayList<>();
    int answer;
    boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        this.info = info;
        this.edges = edges;
        
        for (int i = 0; i < n; i++) {
            routeList.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            routeList.get(edge[0]).add(edge[1]); // 위에서 아래로 단방향
        }
        
        visited = new boolean[n];
        
        visited[0] = true;
        
        dfs(0, 0, 0); // 0번 노드부터 dfs 시작
        
        return answer;        
    }
    
    private void dfs(int now, int sheep, int wolf) {
        if (info[now] == 0) sheep++;
        if (info[now] == 1) wolf++;
        
        if (sheep <= wolf) return;
    
        answer = Math.max(answer, sheep);
        
        // 현재 노드(now)에서 이동할 수 있는 곳
        // 1. 현재 노드의 자식 노드로
        // 2. 되돌아 간 뒤, 되돌아 간 노드의 자식 노드로
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                for (int next : routeList.get(i)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        dfs(next, sheep, wolf);
                        visited[next] = false;
                    }
                    
                }
            }
        }
    }
}