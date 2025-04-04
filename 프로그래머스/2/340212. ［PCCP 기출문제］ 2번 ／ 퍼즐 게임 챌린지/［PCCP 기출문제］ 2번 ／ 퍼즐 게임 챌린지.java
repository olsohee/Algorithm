import java.util.*;

class Solution {
    
    int[] diffs;
    int[] times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        int start = 1;
        int end = 0;
        for (int diff : diffs) {
            end = Math.max(end, diff);
        }
        
        while (start <= end) {
            
            int mid = (start + end) / 2;
            if (canSolve(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return start;
    }
    
    private boolean canSolve(int level) {
        
        long sum = 0; // 전체 퍼즐을 푸는데 걸리는 시간
        
        for (int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i] || i == 0) {
                sum += times[i];
            } else {
                sum += (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            }
        }
        
        if (sum <= limit) return true;
        return false;
    } 
}