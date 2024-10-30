import java.util.*;

class Solution {
    
    List<Integer>[] list;
    int n;
    boolean[] visited;
    int answer;
    
    public int solution(int n, int[][] lighthouse) {
        this.n = n;
        list = new List[n + 1];
        visited = new boolean[n + 1];
        
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int[] light : lighthouse) {
            list[light[0]].add(light[1]);
            list[light[1]].add(light[0]);
        }
        
        // 1번 노드는 루트 노드라고 가정하고, dfs 시작
        dfs(1);
        
        return answer;
    }
    
    private int dfs(int now) {
        
        visited[now] = true;
        
        // 리프노드이면 끄기
        if (list[now].size() == 1 && visited[list[now].get(0)]) {
            return 1;
        }
        
        int sum = 0;
        
        for (int next : list[now]) {
            if (!visited[next]) {
                sum += dfs(next);
            }
        }
        
        if (sum > 0) {
            answer++;
            return 0; // 켜기
        }
        else return 1; // 끄기
    }
}