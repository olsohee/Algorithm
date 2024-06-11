import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String str = Long.toBinaryString(numbers[i]);
            while (!isCollectLength(str)) {
                str = "0" + str;
            }
            
            boolean result = dfs(str);
            answer[i] = result ? 1 : 0;
        }
          
        return answer;
    }
    
    public boolean isCollectLength(String str) {
        int len = str.length() + 1; //len이 2의 제곱이어야 가능
        int n = 1;
        while (n < len) {
            n *= 2;
        }
        return len == n;
    }
    
    public boolean dfs(String str) {
        if (str.length() == 1) {
            return true;
        }
        
        int mid = str.length() / 2;
        String left = str.substring(0, mid);
        String right = str.substring(mid + 1);
        
        if (str.charAt(mid) == '0') {
            if (left.contains("1") || right.contains("1")) {
                return false;
            }
        }
        
        return dfs(left) && dfs(right);
    }
}