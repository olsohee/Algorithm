import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int[] answer = new int[prices.length];
        
        Stack<Info> st = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            if (st.isEmpty()) {
                st.push(new Info(prices[i], i));
                continue;
            }
            
            while (!st.isEmpty() && st.peek().num > prices[i]) {
                Info p = st.pop();
                answer[p.idx] = i - p.idx;
            }
            st.push(new Info(prices[i], i));
       
        }
        
        while (!st.isEmpty()) {
            Info p = st.pop();
            answer[p.idx] = prices.length - 1 - p.idx;
        }
        
        return answer;
    }
    
    public class Info {
        int num;
        int idx;
        public Info(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}