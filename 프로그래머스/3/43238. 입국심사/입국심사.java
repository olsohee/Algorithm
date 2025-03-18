import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // 이분탐색: 모든 사람을 심사하는데 걸리는 최소 시간 찾기
        long start = 0;
        int maxTime = 0;
        for (int time : times) {
            maxTime = Math.max(maxTime, time);
        }
        long end = (long)maxTime * n;
        
        while (start <= end) {
            long mid = (start + end) / 2; 
            long sum = 0; // mid 시간 안에 각 심사대가 심사할 수 있는 사람의 수
            for (int time : times) {
                sum += mid / (long) time;
            }
            if (sum >= n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}