import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        // 첫번째 집 포함
        int[] dp1 = new int[money.length];
        dp1[0] = money[0];
        dp1[1] = money[0];
        
        // 첫번째 집 미포함
        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = money[1];
        
        // dp 배열 채우기
        for (int i = 2; i < money.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        
        // 마지막 집
        int last = money.length - 1;
        // 1. 첫번째 집 포함 + 마지막 집 제외
        int answer = dp1[last - 1];
        // 2. 첫번째 집 미포함
        answer = Math.max(answer, Math.max(dp2[last - 1], dp2[last - 2] + money[last]));
        
        return answer;
    }
}