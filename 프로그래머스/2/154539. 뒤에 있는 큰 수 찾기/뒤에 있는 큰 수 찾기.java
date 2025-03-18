import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
        
        int[] answer = new int[numbers.length];
        Stack<int[]> st = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            if (st.isEmpty()) {
                st.add(new int[]{i, numbers[i]});
                continue;
            } 
            while (!st.isEmpty() && st.peek()[1] < numbers[i]) {
                int[] poll = st.pop();
                answer[poll[0]] = numbers[i];
            }
            
            st.add(new int[]{i, numbers[i]});
        }
        
        while (!st.isEmpty()) {
            int[] poll = st.pop();
            answer[poll[0]] = -1;
        }
        
        return answer;
    }
}