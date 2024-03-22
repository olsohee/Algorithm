import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        int n = money.length;
        
        // 첫 번째 집 터는 경우
        int[] dpO = new int[n];
        
        // 첫 번째 집 안 터는 경우
        int[] dpX = new int[n];
        
        dpO[0] = money[0];
        dpO[1] = dpO[0];
        
        dpX[0] = 0;
        dpX[1] = money[1];
        
        // dp
        for (int i = 2; i <= n - 1; i++) {
            dpO[i] = Math.max(dpO[i - 1], dpO[i - 2] + money[i]);
            dpX[i] = Math.max(dpX[i - 1], dpX[i - 2] + money[i]);
        }
        
        // 마지막 집 털기
        int answer = Math.max(dpO[n - 2], Math.max(dpX[n - 2], dpX[n - 3] + money[n - 1]));
        
        return answer;
    }
}