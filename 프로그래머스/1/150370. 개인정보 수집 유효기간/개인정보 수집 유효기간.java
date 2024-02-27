import java.util.*;

    // 파기해야 할 개인정보 번호를 오름차순으로 
    class Solution {
 
        
        public int[] solution(String today, String[] terms, String[] privacies) {

            Map<String, Integer> map = new HashMap<>();
            for (String term : terms) {
                String name = term.split(" ")[0];
                Integer date = Integer.parseInt(term.split(" ")[1]);
                map.put(name, date);
            }

            int year = Integer.parseInt(today.split("\\.")[0]);
            int month = Integer.parseInt(today.split("\\.")[1]);
            int day = Integer.parseInt(today.split("\\.")[2]);
            int todayNum = year * 12 * 28 + month * 28 + day;


            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < privacies.length; i++) {
                String[] privacy = privacies[i].split(" ");

                int y = Integer.parseInt(privacy[0].split("\\.")[0]);
                int m = Integer.parseInt(privacy[0].split("\\.")[1]);
                int d = Integer.parseInt(privacy[0].split("\\.")[2]);

                int n = y * 12 * 28 + m * 28 + d + map.get(privacy[1]) * 28;

                // 유효기간이 지났는지 확인
                if (todayNum >= n) {
                    list.add(i + 1);
                }

            }
            Collections.sort(list); // 오름차순 정렬
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    
}
