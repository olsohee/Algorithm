import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        if (k >= enemy.length) {
            return enemy.length;
        }
        
        Queue<Integer> que = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for (int i = 0; i < enemy.length; i++) {
            que.add(enemy[i]);
            n -= enemy[i];
            
            if (n < 0) {
                if (k > 0) {
                    n += que.poll();
                    k--;
                } else {
                    return i;
                }
            }
        }
        
        return enemy.length;
    }
}