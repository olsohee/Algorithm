import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        for (int num : queue1) {
            sum1 += num;
            que1.add(num);
        }
        
        for (int num : queue2) {
            sum2 += num;
            que2.add(num);
        }
        
        // 합이 큰 큐에서 poll하기
        long goal = (sum1 + sum2) / 2;
        int count = 0;
        while (sum1 != sum2) {
            if (count >= queue1.length * 4) {
                return -1;
            }
            count++;
            if (sum1 > sum2) {
                int poll = que1.poll();
                que2.add(poll);
                sum1 -= poll;
                sum2 += poll;
            } else {
                int poll = que2.poll();
                que1.add(poll);
                sum2 -= poll;
                sum1 += poll;
            }
        }
        
        return count;
    }
}