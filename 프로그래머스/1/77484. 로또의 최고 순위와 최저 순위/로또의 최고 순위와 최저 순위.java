import java.util.*;

class Solution {
      public int[] solution(int[] lottos, int[] win_nums) {

            // 로또 순위 정하는 방식 map에 저장
            Map<Integer, Integer> map = new HashMap<>();
            map.put(6, 1);
            map.put(5, 2);
            map.put(4, 3);
            map.put(3, 4);
            map.put(2, 5);

            // 당첨 번호 set에 저장
            Set<Integer> set = new HashSet<>();
            for (int num : win_nums) {
                set.add(num);
            }

            // 로또와 당첨 번호 비교
            int correctCnt = 0;
            int zeroCnt = 0;
            for (int num : lottos) {
                if (set.contains(num)) correctCnt++;
                if (num == 0) zeroCnt++;
            }

            int[] answer = new int[2];

            answer[0] = map.getOrDefault(correctCnt + zeroCnt, 6);
            answer[1] = map.getOrDefault(correctCnt, 6);

            return answer;
        }

}