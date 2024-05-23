import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);    
                continue;
            }
            
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - 1 - idx;
        }
        
        // 가격이 떨어지지 않은 시간
        return answer;
    }
}