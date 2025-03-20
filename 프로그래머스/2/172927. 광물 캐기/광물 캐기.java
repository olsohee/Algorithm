import java.util.*;

class Solution {
    
    int[] picks;
    String[] minerals;
    Map<String, Integer> map = new HashMap<>();
    int[][] tiredness = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        
        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                dfs(1, i, 1, tiredness[i][map.get(minerals[0])]);
                picks[i]++;
            }
        }
       
        return answer; // 피로도 최소
    }
    
    // idx: 캘 광물의 인덱스
    // 현재 사용 중인 곡괭이 (0: 다이아, 1: 철, 2: 돌)
    // cnt: 현재 곡괭이로 캔 광물 수 
    // sum: 피로도
    private void dfs(int mineralIdx, int pickIdx, int cnt, int sum) {
        
        // System.out.println();
        // System.out.println();
        // System.out.println("곡괭이 = " + pickIdx + ", 캔 횟수 = " + cnt + ", 피로도 = " + sum);
        // System.out.println("다음에 캘 미네랄 인덱스 = " + (mineralIdx));
        
        
        // 모든 광물을 캔 경우
        if (mineralIdx == minerals.length) {
            answer = Math.min(answer, sum);
            // System.out.println("다 캠, 누적 피로도 = " + sum);
            return;
        }
        
        // 현재 곡괭이로 5번 캤으면, 새로운 곡괭이 쓰기
        if (cnt == 5) {
            boolean isFinish = true;
            for (int i = 0; i < 3; i++) {
                if (picks[i] > 0) {
                    isFinish = false;;
                    picks[i]--;
                    String mineral = minerals[mineralIdx];
                    dfs(mineralIdx + 1, i, 1, sum + tiredness[i][map.get(mineral)]);
                    picks[i]++;
                }
            }
            // 더이상 곡괭이가 없는 경우
            if (isFinish) {
                answer = Math.min(answer, sum);
                // System.out.println("곡괭이없음, 누적 피로도 = " + sum);
                return;
            }
        }
        
        // 기존 곡괭이 쓰기
        else {
            String mineral = minerals[mineralIdx];
            dfs(mineralIdx + 1, pickIdx, cnt + 1, sum + tiredness[pickIdx][map.get(mineral)]);
        }
    }
}