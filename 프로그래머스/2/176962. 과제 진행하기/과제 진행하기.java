import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        
        // que: 시작해야 할 과제들 (시작 시간 빠른 순서대로 정렬)
        Queue<Assignment> que = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Assignment o1, Assignment o2) {
                return o1.start - o2.start;
            }
        }); 
        
        // stack: 하다가 멈춘 과제들
        Stack<Assignment> stack = new Stack<>(); 
        List<String> answerList = new ArrayList<>();
        
        for (String[] plan : plans) {
            String[] time = plan[1].split(":");
            Assignment assignment = new Assignment(plan[0], Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]), Integer.parseInt(plan[2]));
            que.add(assignment);
        }
        
        int now;
        while (!que.isEmpty()) {
            // 1. que에서 꺼내서 과제 시작 (다음 과제 시작 시간 전까지)
            Assignment assignment = que.poll();
            now = assignment.start;
            // 1-1. 과제 끝내기
            if (que.isEmpty()) {
                now += assignment.remain;
                answerList.add(assignment.subject);  
            }
            // 1-2. 다음 과제 시작 전에 끝내거나 중단
            else {
                // 과제 중단: 진행 중이던 과제 다시 que2에 넣고, 다음 과제 시작
                if (assignment.start + assignment.remain > que.peek().start) {
                    assignment.remain -= que.peek().start - assignment.start; 
                    stack.push(assignment);
                    now = que.peek().start;
                    continue;
                }
                // 과제 끝내기
                else {
                    now += assignment.remain;
                    answerList.add(assignment.subject);
                }
            }
            
            
            
            // 2. 다음 과제 전까지 중단된 과제 하기
            if (!que.isEmpty()) {
                while (!stack.isEmpty()) {
                    assignment = stack.pop();
                    if (now + assignment.remain > que.peek().start) {
                        assignment.remain -= que.peek().start - now;
                        stack.push(assignment);
                        now = que.peek().start;
                        break;
                    } else {
                        answerList.add(assignment.subject);
                        now += assignment.remain; 
                    }
                }
            }
        }
        
        // que1에 다음 과제가 없는 경우 중단된 과제 다 하기
        while (!stack.isEmpty()) {
            answerList.add(stack.pop().subject);
        }
        
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    
    class Assignment {
        String subject;
        int start;
        int remain;
        
        public Assignment (String subject, int start, int remain) {
            this.subject = subject;
            this.start = start;
            this.remain = remain;
        }
    }
}