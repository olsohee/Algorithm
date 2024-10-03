import java.util.*;

class Solution {
    public int solution(String s) {
        
        int answer = Integer.MAX_VALUE;
        if (s.length() == 1) return 1;
        
        for (int i = 1; i <= s.length() / 2; i++) {
            String result = "";
            String pre = s.substring(0, i);
            int cnt = 1;
            
            for (int j = i; j <= s.length(); j += i) {
                int endIdx = i + j;
                if (endIdx >= s.length()) endIdx = s.length();
                String now = s.substring(j, endIdx);
                
                if (pre.equals(now)) {
                    cnt++;
                } else {
                    if (cnt >= 2) {
                        result += cnt;
                    } 
                    result += pre;
                    pre = now;
                    cnt = 1;
                }
                
            }
            result += pre;
            answer = Math.min(answer, result.length());
        }
        
        return answer;
    }
}