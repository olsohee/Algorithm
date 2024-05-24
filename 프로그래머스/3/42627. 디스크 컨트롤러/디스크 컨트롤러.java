import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        PriorityQueue<Job> que = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        PriorityQueue<Job> ready = new PriorityQueue<>((o1, o2) -> o1.duration - o2.duration);
        
        for (int[] job : jobs) {
            que.add(new Job(job[0], job[1]));
        }
        
        int cur = 0;
        int sum = 0;
        
        while (!(que.isEmpty() && ready.isEmpty())) {
            // 현재 시간까지 들어온 요청들 ready에 담기
            while (!que.isEmpty() && que.peek().start <= cur) {
                ready.add(que.poll());
            }
            
            if (ready.isEmpty()) {
                cur = que.peek().start;
            } else {
                 Job job = ready.poll();
                cur += job.duration;
                sum += cur - job.start;
            }
           
        }
        
        return sum / jobs.length;
    }
    
    public class Job {
        
        int start;
        int duration;
        
        public Job(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }
    }
}