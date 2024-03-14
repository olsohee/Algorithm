import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 최소한의 객실 수
        int answer = 0; 
        
        // 사용 전 방(우선순위 큐: 시작 시간이 빠른 순으로 정렬)
        Queue<Info> que1 = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                if (o1.start == o2.start) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });
        
        for (String[] time : book_time) {
            String[] start = time[0].split(":");
            String[] end = time[1].split(":");
            que1.add(new Info(Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]), 
                             Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10));
        }
        
        // 사용 중인 방(우선순위 큐: 종료 시간이 빠른 순으로 정렬) (끝나는 시간을 저장함)
        Queue<Integer> que2 = new PriorityQueue<>();
        while (!que1.isEmpty()) {
            Info info = que1.poll();
            // 최초의 방 쓰는 경우
            if (que2.isEmpty()) {
                que2.add(info.end);
                answer++;
            }
            // 새로운 방 쓰는 경우
            else if (!que2.isEmpty() && que2.peek() > info.start) {
                answer++;
                que2.add(info.end);
            } 
            // 기존 방 쓰는 경우
            else {
                que2.poll();
                que2.add(info.end);
            }  
        }
        
        return answer;
    }
    
    class Info {
        int start, end;
        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}