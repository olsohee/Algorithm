import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        
        int deliveryCnt = 0; // 배달할 개수
        int pickupCnt = 0; // 수거할 개수
        
        for (int i = n - 1; i >= 0; i--) {
            deliveryCnt -= deliveries[i];
            pickupCnt -= pickups[i];
            
            int cnt = 0;
            while (deliveryCnt < 0 || pickupCnt < 0) {
                deliveryCnt += cap;
                pickupCnt += cap;
                cnt++;
            }
            
            answer += (i + 1) * cnt * 2;
        }
        
        return answer;
    }
}