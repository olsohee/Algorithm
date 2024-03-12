import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        // score에서 m개 고르기를 반복 (점수가 높은 순으로 먼저)
        Arrays.sort(score);
    
        int answer = 0;
        for (int i = score.length - 1; i >= 0; i -= m) {
            if (i - (m - 1) >= 0) {
                answer += score[i - (m - 1)] * m;
            }
        }
        
        return answer;
    }
}