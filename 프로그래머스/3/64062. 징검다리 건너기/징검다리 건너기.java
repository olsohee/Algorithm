import java.util.*;

class Solution {
    
    int[] stones;
    int k;
    
    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        
        int start = 0;
        int end = 0;
        for (int stone : stones) {
            end = Math.max(end, stone);
        }
        
        int answer =  0;
        while (start <= end) {
            int mid = (start + end) / 2; // mid : 건너는 인원 수
            if (isSuccess(mid)) {
                answer = Math.max(answer, mid); // 성공하면, 답에 반영
                start = mid + 1; // 성공하면, 인원 늘리기
            } else {
                end = mid - 1; // 실패하면, 인원 줄이기
            }
        }
        
        return answer;
    }
    
    private boolean isSuccess(int movePeopleCnt) {
        
        int keepCnt = 1; // 연속으로 건너뛴 개수
        
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] >= movePeopleCnt) {
                keepCnt = 1;
            } else { // 건너뛰기
                if (keepCnt < k) { 
                    keepCnt++;
                } else { // 이미 k만큼 다 건너뛴 경우 실패
                    return false;
                }
            }
        }
        
        return true;
    }
}