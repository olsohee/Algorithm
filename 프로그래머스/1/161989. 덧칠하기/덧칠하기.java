import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        boolean[] painted = new boolean[n + 1];
        
        int answer = 0;
        
        for (int s : section) {
            if (!painted[s]) {
                answer++;
                for (int i = s; i < s + m; i++) {
                    if (i == n + 1) break;
                    painted[i] = true;
                }
            }
        }
        
        return answer;
    }
}