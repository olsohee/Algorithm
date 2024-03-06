import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
            
            Queue<Integer> que = new LinkedList<>();
            for (int i = 0; i < bridge_length; i++) {
                que.add(0);
            }
            
            // 다리 건너기 로직
            int sum = 0;
            int idx = 0;
            int time = 0;
            while (!que.isEmpty()) {
                time++;
                // 빼기
                sum -= que.peek();
                que.poll();
                
                // 더이상 올릴 트럭이 없는 경우
                if (idx >= truck_weights.length) {
                    continue;    
                }
                
                // 무게 체크
                if (sum + truck_weights[idx] <= weight) {
                    // 트럭 올리기
                    que.add(truck_weights[idx]);
                    sum += truck_weights[idx];
                    idx++;
                } else {
                    // 트럭 대신 0 올리기
                    que.add(0);
                }
            }
            
            return time;
        }
}