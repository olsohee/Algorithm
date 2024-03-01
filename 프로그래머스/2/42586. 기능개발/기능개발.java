import java.util.*;
class Solution {
     public int[] solution(int[] progresses, int[] speeds) {
            // 각 작업이 완성되는데 걸리는 시간 계산
            int[] days = new int[progresses.length];
            for (int i = 0; i < progresses.length; i++) {
                int day = (100 - progresses[i]) / speeds[i];
                if ((100 - progresses[i]) % speeds[i] != 0) {
                    day++;
                }
                days[i] = day;
            }

            // 배포 날짜 계산
            Stack<Integer> stack = new Stack<>();
            List<Integer> answerList = new ArrayList<>();
            for (int i = 0; i < days.length; i++) {
                // 스택이 비어있으면 배포 시작
                if (stack.isEmpty()) {
                    stack.add(days[i]);
                    answerList.add(1);
                    continue;
                }
                // peek한 값이 현재값보다 크거나 같으면 함께 배포
                if (stack.peek() >= days[i]) {
                    Integer num = answerList.get(answerList.size() - 1);
                    answerList.remove(answerList.size() - 1);
                    answerList.add(num + 1);
                } else {
                    // peek한 값이 현재값보다 작으면 새로 배포 시작
                    stack.add(days[i]);
                    answerList.add(1);
                }
            }

            int[] answer = new int[answerList.size()];
            for (int i = 0; i < answerList.size(); i++) {
                answer[i] = answerList.get(i);
            }
            return answer;
        }
}