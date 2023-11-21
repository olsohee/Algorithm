import java.time.LocalDate;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {

        List<Integer> answerList = new ArrayList<>();

        // 유효 기간 map에 저장
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] split = terms[i].split(" ");
            map.put(split[0], Integer.parseInt(split[1]));
        }

        // 현재 날짜 생성
        String[] split = today.split("\\.");
        LocalDate currentDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));

        // 개인정보 for문으로 돌면서 오늘 날짜와 폐기 날짜 비교
        for(int i = 0; i < privacies.length; i++) {
            String pri = privacies[i];
            split = pri.split("\\.");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]);
            String[] split2 = split[2].split(" ");
            int date = Integer.parseInt(split2[0]);
            String term = split2[1];

            LocalDate createDate = LocalDate.of(year, month, date);

            // 폐기 날짜 생성
            Integer termMonth = map.get(term);
            LocalDate deleteDate = createDate.plusMonths(termMonth);

            // 폐기 날짜와 현재 날짜 비교해서 폐기되어야 할 것 분류하기 (현재 날짜보다 폐기 날짜가 더 작거나 같을 때)
            if(deleteDate.isBefore(currentDate) || deleteDate.compareTo(currentDate) == 0) {
                answerList.add(i + 1);
            }
        }

        // 정답 리스트 정렬 후 배열로 옮기기
        Collections.sort(answerList);
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
