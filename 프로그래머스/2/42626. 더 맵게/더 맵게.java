import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        // 우선순위 큐에 음식 넣기 (스코빌 지수가 작을수록 우선순위가 높음)
        Queue<Integer> que = new PriorityQueue();
        for(int i = 0; i < scoville.length; i++) {
            que.add(scoville[i]);
        }
        
        int answer = 0;
        while (!que.isEmpty()) {
            // 스코빌 지수가 제일 작은 1개가 k와 같거나 크면 끝내기
            if (que.peek() >= K) {
                return answer;
            }
            
            // 큐 사이즈가 2개 이상일 때만 진행 가능
            if (que.size() < 2) {
                break;
            }
            // 스코빌 지수 작은 2개를 큐에서 꺼내서, 섞기, 섞은 스코빌 지수를 큐에 넣기
            int newScoville = que.poll() + que.poll() * 2;
            que.add(newScoville);
            answer++;   
        }
        return -1;
    }
}