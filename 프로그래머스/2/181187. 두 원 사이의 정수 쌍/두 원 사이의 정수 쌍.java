import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        
        long answer = 0;
        long duplicatedCnt = 0;
        
        for (int a = 0; a <= r2; a++) {
            long cnt = 0;
            long b1 = (long)Math.sqrt(Math.pow(r2, 2) - Math.pow(a, 2));
            if (a > r1) {
                cnt = b1 + 1;
            } else {
                long b2 = (long)Math.sqrt(Math.pow(r1, 2) - Math.pow(a, 2));
                if (b2 * b2 < Math.pow(r1, 2) - Math.pow(a, 2)) {
                    b2 += 1;
                }
                cnt =  b1 - b2 + 1;
            }
            if (a == 0) {
                duplicatedCnt = cnt;
            }
            answer += cnt;
        }
        return answer * 4 - duplicatedCnt * 4;
    }
}