import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        Queue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순
        for (int work : works) {
            que.add(work);
        }
        
        for (int i = 0; i < n; i++) {
            if (que.isEmpty()) break;
            int work = que.poll();
            if (work - 1 > 0) {
                que.add(work - 1);
            }
        }
        
        long answer = 0;
        while (!que.isEmpty()) {
            answer += Math.pow(que.poll(), 2);
        }
        return answer;
    }
}