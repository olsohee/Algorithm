import java.util.*;
class Solution {
     public int[] solution(int[] progresses, int[] speeds) {

            Queue<Integer> q = new LinkedList<>();
            List<Integer> answerList = new ArrayList<>();

            for (int i = 0; i < progresses.length; i++) {
                // 각 작업이 완성되는데 걸리는 시간 계산
                int day = (int)Math.ceil((100 - progresses[i]) / (double)speeds[i]);

                // 배포 날짜 계산
                if (!q.isEmpty() && q.peek() < day) {
                    answerList.add(q.size());
                    q.clear();
                }
                q.add(day);
            }

            answerList.add(q.size());

            int[] answer = new int[answerList.size()];
            for (int i = 0; i < answerList.size(); i++) {
                answer[i] = answerList.get(i);
            }
            return answer;
        }
}