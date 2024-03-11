import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
    
        Stack<Point> stack = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new Point(numbers[i], i));
                continue;
            }
            
            // 넣으려는 값이 스택에서 peek한 값보다 더 큰 값인 경우 -> 스택에 값 빼기
            while (!stack.isEmpty() && stack.peek().num < numbers[i]) {
                Point point = stack.pop();
                answer[point.idx] = numbers[i];
            }
            stack.push(new Point(numbers[i], i));
        }
        
        // stack에 남아 있는 값 처리하기
        while (!stack.isEmpty()) {
            answer[stack.pop().idx] = -1;
        }
        
        return answer;
    }

    class Point {
        int num;
        int idx;
        public Point (int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}