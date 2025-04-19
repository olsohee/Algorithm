import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        int maxTime = 0;
        for (int time : times) {
            maxTime = Math.max(maxTime, time);
        }
        
        long start = 0;
        long end = (long)maxTime * n;
        
        while (start <= end) {
            long mid = (start + end) / 2; // 끝나는 시간
            
            long sum = 0; // mid 시간 안에 심사 가능한 사람 수
            for (int time : times) {
                sum += mid / time;
            }
            
            if (sum < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return start;
    }
}