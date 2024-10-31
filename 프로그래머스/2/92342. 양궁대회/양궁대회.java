import java.util.*;

class Solution {
    
    int n;
    int[] info;
    int maxDiff;
    int[] answer;
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        // 라이언이 화살을 쏜 결과 조합 생성 (11개 중 n발 고르기)
        dfs(0, n, new int[11]);
        
        // 무조건 비기거나 지면 -1 반환
        if (maxDiff == 0) return new int[]{-1};
    
        return answer;
    }
    
    private void dfs(int idx, int remain, int[] result) {
        // 다 쏜 경우
        if (idx == 11) {
            
            if (remain == 0) {
                getResult(result);
            }
            return;
        }
        
        // 인덱스 idx 점수에 i발 쏘기
        for (int i = 0; i <= remain; i++) {
            result[idx] = i;
            dfs(idx + 1, remain - i, result);
        }
    }
    
    private void getResult(int[] result) {
        int apeach = 0;
        int lion = 0;
        for (int i = 0; i <= 10; i++) {
            if (info[i] == 0 && result[i] == 0) {
                continue; // 둘 다 못 쏘면, 둘 다 점수 획득 불가
            } 
            if (info[i] >= result[i]) { // 쏜 횟수가 같으면 라이언이 점수 획득
                apeach += 10 - i;
            } else {
                lion += 10 - i;
            }
        }
        
        if (lion > apeach) {
            // 가장 큰 점수 차이인 경우가 여러개이면, 가장 낮은 점수를 더 많이 맞힌 경우를 반환
            if (maxDiff == lion - apeach) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > result[i]) {
                        break;
                    } 
                    if (result[i] > answer[i]) {
                        answer = result.clone();
                        break;
                    }
                }
            }
            if (maxDiff < lion - apeach) {
                maxDiff = lion - apeach;
                answer = result.clone();
            }
        }
    }
}