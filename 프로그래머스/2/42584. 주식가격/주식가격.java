import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
             stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = n - 1 - idx;
        }
        
        return answer;
    }
}