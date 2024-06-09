import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        int d = 0;
        int p = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            d -= deliveries[i];
            p -= pickups[i];
            
            // d와 p 둘 중 하나라도 음수이면 물류창고 다녀와야 함
            int cnt = 0;
            while (d < 0 || p < 0) {
                d += cap;
                p += cap;
                cnt++;
            }
            
            answer += (i + 1) * cnt * 2;
        }
        
        return answer;
    }
}