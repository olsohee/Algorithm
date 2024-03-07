import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        // que1: 작업을 요청 순서대로 정렬
        Queue<Job> que1 = new PriorityQueue(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                // 요청시간이 같으면, 소요시간이 짧은 것이 먼저 처리되도록
                if (o1.start == o2.start) {
                    return o1.time - o2.time;
                }
                return o1.start - o2.start;
            }
        });
        
        for (int[] job : jobs) {
            que1.add(new Job(job[0], job[1]));
        }
        
        // que2: 작업을 소요시간대로 정렬
        Queue<Job> que2 = new PriorityQueue(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                // 소요시간이 같으면, 먼저 요청된 것이 먼저 처리되도록
                if (o1.time == o2.time) {
                    return o1.start - o2.start;
                }
                return o1.time - o2.time;
            }
        });
        
        // 첫 번째 작업 수행
        Job poll = que1.poll();
        int now = poll.start + poll.time;
        int sum = now - poll.start;
        
        while (!que1.isEmpty() || !que2.isEmpty()) {
            
            // 현재 시간까지 들어온 요청을 que에 넣기
            while (!que1.isEmpty()) {
                if (now < que1.peek().start) {
                    break;
                } else {
                    que2.add(que1.poll());
                }
            }
            
            // 만약 요청이 안들어왔으면, 다음 요청까지 시간 보내기
            if (que2.isEmpty()) { 
                now = que1.peek().start;
                continue;
            }
        
            // que2에 있는 작업들 중 소요시간이 가장 작은 작업 수행하기
            Job job = que2.poll();
            now += job.time;
            sum += now - job.start;
        }
        return sum / jobs.length;
        
    }
    
    class Job {
        int start;
        int time;
        public Job (int start, int time) {
            this.start = start;
            this.time = time;
        }
    }
}