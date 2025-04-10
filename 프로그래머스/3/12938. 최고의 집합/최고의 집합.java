import java.util.*;

class Solution {
    
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }
        
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            answer[i] = s / (n - i);
            s -= s / (n - i);
        }
        return answer;
    }
}