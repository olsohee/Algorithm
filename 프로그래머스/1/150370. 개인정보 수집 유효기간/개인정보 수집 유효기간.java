import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> answerList = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            map.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }
        
        int todayInt =  Integer.parseInt(today.split("\\.")[0]) * 12 * 28
            + Integer.parseInt(today.split("\\.")[1]) * 28
            + Integer.parseInt(today.split("\\.")[2]);
        
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i].split(" ")[0];
            int term = map.get(privacies[i].split(" ")[1]) * 28;
            
            int start = Integer.parseInt(privacy.split("\\.")[0]) * 12 * 28 
                + Integer.parseInt(privacy.split("\\.")[1]) * 28 
                + Integer.parseInt(privacy.split("\\.")[2]);
            
            if (todayInt >= start + term) {
                answerList.add(i + 1);
            }
        }
        
        // 파기할 번호를 오름차순으로
        return answerList.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}