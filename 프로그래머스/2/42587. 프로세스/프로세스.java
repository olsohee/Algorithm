import java.util.*;

class Solution {
       public int solution(int[] priorities, int location) {
            Queue<Info> que = new LinkedList<>();
            for (int i = 0; i < priorities.length; i++) {
                que.add(new Info(priorities[i], i));
            }
            List<Integer> list = new ArrayList<>();
            for (int i : priorities) {
                list.add(i);
            }
            Collections.sort(list, Collections.reverseOrder());

            int idx = 0;
            int maxPri = list.get(idx);
            int answer = 0;
            while (!que.isEmpty()) {
                Info info = que.poll();
                // 우선순위의 숫자가 아니면 다시 큐에 넣기
                if (info.priority != maxPri) {
                    que.add(info);
                    continue;
                }
                // 우선순위의 숫자인 경우
                answer++;
                if (info.location == location) {
                    break;
                }
                maxPri = list.get(++idx);
            }
            
            return answer;
        }
        
        class Info {
            int priority;
            int location;

            public Info(int priority, int location) {
                this.priority = priority;
                this.location = location;
            }
        }
}