import java.util.*;

class Solution {
    List<String> answerList = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {

        // 각 order 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] charArray = orders[i].toCharArray();
            Arrays.sort(charArray);
            orders[i] = String.valueOf(charArray);
        }

        // 메뉴 개수만큼 각 메뉴에서 조합 만들어서 map에 담기
        for (int cnt : course) {
            for (String order : orders) {
                combination("", order, cnt);
            }
            // 가장 많은 조합을 answerList에 저장
            if(!map.isEmpty()) {
                List<Integer> countList = new ArrayList<>(map.values());
                Integer maxCount = Collections.max(countList);

                if(maxCount > 1) {
                    for(String key : map.keySet()) {
                        if(map.get(key) == maxCount) {
                            answerList.add(key);
                        }
                    }
                }
            }
            map.clear();
        }

        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private void combination(String result, String others, int cnt) {
        if(cnt == 0) {
            map.put(result, map.getOrDefault(result, 0) + 1);
            return;
        }

        for(int i = 0; i < others.length(); i++) {
            combination(result + others.charAt(i), others.substring(i+1), cnt - 1);
        }

    }
}