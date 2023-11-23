import java.util.*;

/*
- 파기해야 할 개인정보의 번호를 "오름차순"으로
 */

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {

        Map<String, Integer> termsMap = new HashMap<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i].split(" ")[0];
            Integer date = Integer.parseInt(terms[i].split(" ")[1]);
            termsMap.put(term, date);
        }

        // 파기 날짜 계산 + 오늘 날짜와 비교
        int todayDate = 0;
        todayDate += Integer.parseInt(today.split("\\.")[0]) * 12 * 28;
        todayDate += Integer.parseInt(today.split("\\.")[1]) * 28;
        todayDate += Integer.parseInt(today.split("\\.")[2]);

        for (int i = 0; i < privacies.length; i++) {
            String createDate = privacies[i].split(" ")[0];
            String term = privacies[i].split(" ")[1];

            int createDateInt = 0;
            createDateInt += Integer.parseInt(createDate.split("\\.")[0]) * 12 * 28;
            createDateInt += Integer.parseInt(createDate.split("\\.")[1]) * 28;
            createDateInt += Integer.parseInt(createDate.split("\\.")[2]);

            int deleteDate = createDateInt + termsMap.get(term) * 28;

            if (deleteDate <= todayDate) {
                answerList.add(i + 1);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
