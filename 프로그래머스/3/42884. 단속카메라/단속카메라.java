import java.util.*;

class Solution {
    
    int answer = 0;
    
    public int solution(int[][] routes) {
        
        Queue<Time> que = new PriorityQueue<>();
        for (int[] route : routes) {
            que.add(new Time(route[0], route[1]));
        }
        
        while (!que.isEmpty()) {
            answer++;
            Time time = que.poll(); // 처리해야 하는 루트
            int start = time.start;
            int end = time.end;
            while (!que.isEmpty()) {
                // 시간 겹치는지 확인
                if (que.peek().start <= end) { 
                    Time poll = que.poll();
                    start = Math.max(start, poll.start);
                    end = Math.min(end, poll.end);
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public class Time implements Comparable<Time> {
        int start;
        int end;
        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo (Time o) {
            return this.start - o.start;
        }
    }
}