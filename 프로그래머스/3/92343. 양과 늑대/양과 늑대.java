import java.util.*;

class Solution {
    
    int answer = 0;
    int[] info;
    int[][] edges;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        
        // 루트 노드(0번)부터 시작
        dfs(0, new boolean[info.length], 0, 0);
        
        // 루트 노드로 돌아왔을 때 양의 최대 갯수
        return answer;
    }
    
    private void dfs(int now, boolean[] visited, int sheep, int wolf) {
        visited[now] = true;
        
        if (info[now] == 0) sheep++;
        else wolf++;
        
        if (wolf >= sheep) return;
        
        answer = Math.max(answer, sheep);
        
        // 현재 위치에서 더 깊게 이동 OR 지금까지 지나온 길에서 더 깊게 이동
        for (int[] edge : edges) {
            // 부모 노드 방문했고 자식 노드 방문 안했으면, 그 자식 노드를 방문하기 
            // 이때 부모 노드는 이미 지나온 길들
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = visited.clone();
                // boolean[] newVisited = new boolean[info.length];
                // for (int i = 0; i < info.length; i++) {
                //     newVisited[i] = visited[i];
                // }
                dfs(edge[1], newVisited, sheep, wolf);
            }
        }
    }
}