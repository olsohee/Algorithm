import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        // 섞는 최소 횟수 (불가능: -1)
        int answer = 0;
        
        // 우선순위 큐: 숫자가 작은 게 우선
        Queue<Integer> que = new PriorityQueue<>();
        for (int num : scoville) {
            que.add(num);
        } 
        
        // 큐에서 peek한 값이 K 이상이면 끝내기
        while (que.peek() < K) {
            // 큐 사이즈가 1개인데 K 미만이면, 불가능하므로 -1 반환
            if (que.size() == 1) {
                return -1;
            }
            // K 미만이면 2개 꺼내서 섞고, 넣기
            que.add(que.poll() + que.poll() * 2);
            answer++;
        }
        
        
        return answer;
    }
}