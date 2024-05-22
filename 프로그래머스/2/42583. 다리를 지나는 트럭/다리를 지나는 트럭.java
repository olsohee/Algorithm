import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            que.add(0);
        }
        
        int sec = 0;
        int sum = 0;
        int idx = 0;
        while (!que.isEmpty()) {
            sec++;
            int poll = que.poll();
            sum -= poll;
            if (idx < truck_weights.length) {
                if (sum + truck_weights[idx] <= weight) {
                    que.add(truck_weights[idx]);
                    sum += truck_weights[idx];
                    idx++;
                } else {
                    que.add(0);
                }
            } 
        }
        return sec;
    }
}