

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        long totalSum = 0;
        long que1Sum = 0;

        for (int i = 0; i < queue1.length; i++) {
            que1.add(queue1[i]);
            que2.add(queue2[i]);
            que1Sum += queue1[i];
            totalSum += (queue1[i] + queue2[i]);
        }

        if(totalSum % 2 != 0) {
            return -1;
        }

        int cnt = 0;
        long goal = totalSum / 2;

        while(que1Sum != goal) {
            if(cnt == queue1.length * 4) {
                return -1;
            }

            if(que1Sum > goal) {
                Integer value = que1.poll();
                que1Sum -= value;
                que2.add(value);
            } else {
                Integer value = que2.poll();
                que1Sum += value;
                que1.add(value);
            }
            cnt++;
        }

        return cnt;
    }
}

