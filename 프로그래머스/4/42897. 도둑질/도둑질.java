import java.util.*;

class Solution {
    public int solution(int[] money) {
        
        int answer = 0;
        
        // 첫 번째 집을 터는 경우
        int[] dpWithFirst = new int[money.length];
        // 첫 번째 집을 털지 않는 경우
        int[] dpWithoutFirst = new int[money.length];
        
        dpWithFirst[0] = money[0];
        dpWithFirst[1] = Math.max(dpWithFirst[0], money[1]);
        dpWithoutFirst[1] = money[1];
        
        for (int i = 2; i < money.length; i++) {
            if (i == money.length - 1) {
                dpWithFirst[i] = dpWithFirst[i - 1];
                dpWithoutFirst[i] = Math.max(dpWithoutFirst[i - 1], dpWithoutFirst[i - 2] + money[i]);
                answer = Math.max(dpWithFirst[i], dpWithoutFirst[i]);
                break;
            } 
            dpWithFirst[i] = Math.max(dpWithFirst[i - 1], dpWithFirst[i - 2] + money[i]);
            dpWithoutFirst[i] = Math.max(dpWithoutFirst[i - 1], dpWithoutFirst[i - 2] + money[i]);
        }
        
        return answer;
    }
}