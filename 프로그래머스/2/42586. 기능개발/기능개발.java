import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] days = new int[n];
        
        for (int i = 0; i < n; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                day++;
            }
            days[i] = day;
        }
        
        List<Integer> list = new ArrayList<>();
        
        Stack<Integer> stack = new Stack<>();
        stack.push(days[0]);
        int max = days[0];
        
        for (int i = 1; i < n; i++) {
            if (days[i] <= max) {
                stack.push(days[i]);
            } else {
                int cnt = 0;
                while (!stack.isEmpty()) {
                    stack.pop();
                    cnt++;
                }
                list.add(cnt);
                stack.push(days[i]);
                max = days[i];
            }
        }
        
        list.add(stack.size());
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
         
        return answer;
    }
}