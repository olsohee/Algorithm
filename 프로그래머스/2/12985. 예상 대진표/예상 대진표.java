import java.util.*;

class Solution {
    public int solution(int n, int a, int b) {
        int round = 1;
        while (true) {
            // 두 수의 차이가 1이면서, 큰 값이 짝수이면 끝
            if (Math.abs(a - b) == 1 &&  Math.max(a, b) % 2 == 0) {
                break;
            }
            
            if (a != 1) {
                a = (int) Math.ceil((double)a / 2);
            }
            if (b != 1) {
                b = (int) Math.ceil((double)b / 2);
            }
            
            round++;
        }
        
        return round;
    }
}