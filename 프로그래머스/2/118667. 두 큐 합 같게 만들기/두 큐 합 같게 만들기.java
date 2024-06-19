import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length * 2;
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        long total = 0;
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i = 0; i < n / 2; i++) {
            que1.add(queue1[i]);
            sum1 += queue1[i];
            
            que2.add(queue2[i]);
            sum2 += queue2[i];
            
            total += queue1[i] + queue2[i];
        }
        
        int answer = 0;
        while (sum1 != sum2) {
            if (answer > n * 2) {
                answer = -1;
                break;
            }
            answer++;
            if (sum1 > sum2) {
                int num = que1.poll();
                sum1 -= num;
                sum2 += num;
                que2.add(num);
            } else {
                int num = que2.poll();
                sum2 -= num;
                sum1 += num;
                que1.add(num);
            }
        }
        // 최소 횟수 반환 (원소 합을 같게 만들 수 없으면 -1 반환)
        return answer;
    }
}