import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        
        int start = 0;
        int end = 0;
        for (int stone : stones) {
            end = Math.max(end, stone);
        }
        
        // 이분탐색으로 건널 수 있는 사람의 최대 수 구하기
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canMove(stones, mid, k)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }
    
    private boolean canMove(int[] stones, int people, int k) {
        int cnt = 0; // 연속으로 건너뛰는 돌의 수
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] >= people) { // 건너는 경우
                cnt = 0;
            } else { // 건너뛰는 경우
                cnt++;
                if (cnt == k) return false;
            }
        }
        return true;
    }
}