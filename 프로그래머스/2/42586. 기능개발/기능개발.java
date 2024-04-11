import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int[] days = new int[progresses.length];
        
        // 1. 몇일 뒤에 배포되는지 계산
        for (int i = 0; i < progresses.length; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                day++;
            }
            days[i] = day;
        }
        
        // 2. 배포 날짜를 스택에 반영
        int maxDay = 0;
        for (int i = 0; i < days.length; i++) {
            if (stack.isEmpty()) {
                stack.push(days[i]);
                maxDay = days[i];
                continue;
            }
            // peek한 값보다 작거나 같으면 스택에 넣기
            if (maxDay >= days[i]) {
                stack.push(days[i]);
            } 
            
            // peek한 값보다 크면 스택에 있는 것들 다 빼서 배포 + 현재 값 스택에 넣기
            else {
                list.add(stack.size());
                stack.clear();
                
                stack.push(days[i]);
                maxDay = days[i];
            }
        }
       
        // 마지막에 스택에 남은 것들 다 빼서 배포
        list.add(stack.size());
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}