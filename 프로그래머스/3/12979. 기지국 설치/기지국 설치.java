import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int last = 0;
        int len = w * 2 + 1;
        for (int station : stations) {
            // last ~ station - w - 1;
            int start = station - w;
            int end = station + w;
            if (last >= start) {
                last = end;
                continue;
            }
            
            int dist = start - last - 1;
            
            answer += dist / len;
            if (dist % len > 0) answer++;
            
            last = end;
        }
        // // System.out.println("last = " + last);
        
        if (last < n) {
            int dist = n - last;
            // System.out.println(dist);
            answer += dist / len;
            if (dist % len > 0) answer++;
        }
        return answer;
    }
}