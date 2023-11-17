import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {

        String answer = "";

        // map에 이름과 몇명인지 저장
        Map<String, Integer> map = new HashMap<>();
        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        // 완주한 사람들 map에서 제거
        for (String name : completion) {
            map.put(name, map.get(name) - 1);
        }

        // 완주하지 못한 사람 출력
        for(String key : map.keySet()) {
            if(map.get(key) >= 1) {
                answer += key;
            }
        }
        return answer;
    }
}