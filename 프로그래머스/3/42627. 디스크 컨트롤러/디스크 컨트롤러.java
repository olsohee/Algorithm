import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        // que1: 작업을 요청 시간 기준으로 오름차순
        Queue<Job> que1 = new PriorityQueue(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.start == o2.start) {
                    return o1.duration - o2.duration;
                }
                return o1.start - o2.start;
            }
        });
        
        for (int[] job : jobs) {
            que1.add(new Job(job[0], job[1]));
        }
        
        // que2: 작업을 소요 시간 기준으로 오름차순
        Queue<Job> que2 = new PriorityQueue(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.duration - o2.duration;
            }
        });
        
        // 첫 번째 작업부터 시작
        Job job = que1.poll();
        int now = job.start + job.duration;
        int sum = job.duration;
        
        while (!(que1.isEmpty() && que2.isEmpty())) {
            
            // 현재 시간까지 들어온 작업들을 que1에서 que2로 옮기기
            while (!que1.isEmpty()) {
                if (que1.peek().start <= now) {
                    que2.add(que1.poll());
                } else {
                    break;
                }
            }
            
            // 아직 들어온 요청이 없으면 그 시간까지 now값 갱신
            if (que2.isEmpty()) {
                now = que1.peek().start;
                continue;
            }
            
            // que2에서 가장 소요시간이 적은 작업 1개 수행하기
            job = que2.poll();
            now += job.duration;
            sum += now - job.start;
        }        
        
        return sum / jobs.length;
    }
    
    class Job {
        int start;
        int duration;
        public Job (int start, int duration) {
            this.start = start;
            this.duration = duration;
        }
    }
}