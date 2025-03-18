import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        int[] computers = new int[24];
        
        for (int i = 0; i < 24; i++) {
            if (players[i] == 0) continue;
            int needCnt = players[i] / m;
            if (computers[i] >= needCnt) continue;
            int addedCnt = needCnt - computers[i];
            answer += addedCnt;
            for (int j = i; j < i + k; j++) {
                if (j == 24) break;
                computers[j] += addedCnt;
            }
        }
        
        return answer;
    }
}