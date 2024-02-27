import java.util.*;

    // 파기해야 할 개인정보 번호를 오름차순으로 
    class Solution {
       
        public int[] solution(String today, String[] terms, String[] privacies) {

            String[] arr = today.split("\\.");
            int todayNum = Integer.parseInt(arr[0]) * 12 * 28 + Integer.parseInt(arr[1]) * 28 + Integer.parseInt(arr[2]);

            Map<String, Integer> map = new HashMap<>();
            for (String term : terms) {
                String[] termArr = term.split(" ");
                map.put(termArr[0], Integer.parseInt(termArr[1]));
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < privacies.length; i++) {
                String[] privacy = privacies[i].split(" ");

                String[] dayArr = privacy[0].split("\\.");
                int year = Integer.parseInt(dayArr[0]);
                int month = Integer.parseInt(dayArr[1]);
                int day = Integer.parseInt(dayArr[2]);

                int num = year * 12 * 28 + month * 28 + day;
                num += map.get(privacy[1]) * 28;

                // 유효기간이 지났는지 확인
                if (todayNum >= num) {
                    list.add(i + 1);
                }

            }
            Collections.sort(list);
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        } 
    
}
