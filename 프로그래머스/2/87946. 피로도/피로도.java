import java.util.*;

class Solution {
    
    int n; // 던전 수
    int k;
    int[][] dungeons;
    int answer;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        this.n = dungeons.length;
        this.k = k;
        this.dungeons = dungeons;
        visited = new boolean[n];
        
        dfs(new ArrayList<>()); // 각 던전들의 순서 조합 만들기
        
        return answer;
    }
    
    private void dfs(List<Integer> order) {
        if (order.size() == n) {
            // 해당 순서에서 피로도 계산하기
            play(order);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            order.add(i);
            dfs(order);
            order.remove((Object)i);
            visited[i] = false;
        }
    }
    
    private void play(List<Integer> order) {
        int tiredness = k;
        int round = 0;
        
        for (int i = 0; i < order.size(); i++) {
            if (tiredness < dungeons[order.get(i)][0]) {
                break;
            }
            round++;
            tiredness -= dungeons[order.get(i)][1];
        }
        answer = Math.max(answer, round);
    }
}