import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, List<String>> declareMap = new HashMap<>();
        Map<String, Integer> mailMap = new HashMap<>();

        // map 초기화
        for (String name : id_list) {
            declareMap.put(name, new ArrayList<>());
            mailMap.put(name, 0);
        }

        // 신고 내역 반영
        for (String s : report) {
            String name1 = s.split(" ")[0]; // 신고하는 사람
            String name2 = s.split(" ")[1]; // 신고 당한 사람

            List<String> list = declareMap.get(name2);
            if(!list.contains(name1)) {
                list.add(name1);
                declareMap.put(name2, list);
            }
        }

        // k보다 같거나 크게 신고 당한 사람은 이용 정지
        for (String key : declareMap.keySet()) {
            List<String> list = declareMap.get(key);
            if(list.size() >= k) {
                for (String name : list) {
                    Integer mailCnt = mailMap.get(name);
                    mailMap.put(name, mailCnt + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            answer[i] = mailMap.get(id_list[i]);
        }

        return answer;
    }
}
