import java.util.*;

class Solution {
    
    int answer = Integer.MAX_VALUE;
    int[] picks;
    String[] minerals;
    int[][] value = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    Map<String, Integer> map = new HashMap<>();
    
    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                // dfs(i, 0, 0, 0);
                dfs(i, 1, value[i][map.get(minerals[0])], 1);
                picks[i]++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int weapon, int idx, int sum, int cnt) {
       
        if (idx == minerals.length) { // 모든 광물을 캔 경우 끝!
            answer = Math.min(answer, sum);
            return;
        }
        
        if (cnt == 5) {
            // 새로운 곡괭이 사용
            boolean isFinish = true;
            for (int i = 0; i < 3; i++) {
                if (picks[i] > 0) {
                    isFinish = false;
                    picks[i]--;
                    String mineral = minerals[idx];
                    dfs(i, idx + 1, sum + value[i][map.get(mineral)], 1);
                    picks[i]++;
                }
            }
            if (isFinish) { // 모든 곡괭이를 사용한 경우 끝!
                answer = Math.min(answer, sum);
                return;
            }
        } else {
            // 기존 곡괭이 사용
            String mineral = minerals[idx];
            dfs(weapon, idx + 1, sum + value[weapon][map.get(mineral)], cnt+1);
        }
    }
}