import java.util.*;

class Solution {
    
    List<Set<Integer>> route = new ArrayList<>();
    int n;
    int[] visited; // 0: 방문 안함, 1: 등대 끔, 2: 등대 켬
    
    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        visited = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            route.add(new HashSet<>());
        }
        
        for (int[] light : lighthouse) {
            route.get(light[0]).add(light[1]);
            route.get(light[1]).add(light[0]);
        }
        
        // 1번 노드는 루트 노드라고 가정하고, dfs 시작
        dfs(1);
        
        int answer = 0;
        for (int i = 0; i <= n; i++) {
            if (visited[i] == 2) {
                answer++;
            }
        }
        return answer;
    }
    
    private void dfs(int now) {
        
        visited[now] = 1; // 기본으로 등대 끔
        
        for (int next : route.get(now)) {
            if (visited[next] == 0) {
                dfs(next);
                if (visited[next] == 1) { // 루트 노드가 끄면, 현재 노드는 켜기
                    visited[now] = 2;
                }
            }
        }
    }
}