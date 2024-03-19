import java.util.*;

class Solution {
    
    int answer = 0;
    
    public int solution(int[][] jobs) {
        
        // que1 (요청순서대로 정렬)
        Queue<Job> que1 = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.request == o2.request) {
                    return o1.duration - o2.duration;
                }
                return o1.request - o2.request;
            }
        });
        
        // que2 (작업시간대로 정렬)
        Queue<Job> que2 = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.duration - o2.duration;
            }
        });
        
        // que1에 값 채우기
        for (int[] job : jobs) {
            que1.add(new Job(job[0], job[1]));
        }
        
        int now = 0;
        while (!que1.isEmpty() || !que2.isEmpty()) {
            // 1. 현재 시간을 기준으로 들어온 요청을 que1에서 que2로 옮기기  
            while (!que1.isEmpty()) {
                if (que1.peek().request <= now) {
                    que2.add(que1.poll());
                } else {
                    break;
                }
            }

            if (que2.isEmpty()) {
                // 2. que2가 비어있다면, 아직 현재시간까지 들어온 요청이 없으므로 que1에서 peek한 요청 시간까지 현재 시간 보내기        
                now = que1.peek().request;
            } else {
                // 3. que2에서 작업시간이 가장 적은 1개 처리하기
                Job job = que2.poll();
                now += job.duration;
                answer += (now - job.request);                
            }
        }

        return answer / jobs.length;
    }
    
    public class Job {
        int request;
        int duration;
        
        public Job(int request, int duration) {
            this.request = request;
            this.duration = duration;
        }
    }
}