import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answerList = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            termMap.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }
        
        int todayInt = convertToInt(today);
        for (int i = 0; i < privacies.length; i++) {
            int start = convertToInt(privacies[i].split(" ")[0]);
            int end = start + termMap.get(privacies[i].split(" ")[1]) * 28;
            if (end <= todayInt) {
                answerList.add(i + 1);
            }
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    private int convertToInt(String date) {
        int year = Integer.parseInt(date.split("\\.")[0]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int day = Integer.parseInt(date.split("\\.")[2]);
        return year * 12 * 28 + month * 28 + day;
    }
}