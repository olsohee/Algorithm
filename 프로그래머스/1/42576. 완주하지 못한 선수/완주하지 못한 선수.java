import java.util.*;

class Solution {
            public String solution(String[] participant, String[] completion) {

            // Map에 참여자 저장
            Map<String, Integer> map = new HashMap();
            for (String s : participant) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            
            // 완주한 선수 제거
            for (String s : completion) {
                map.put(s, map.get(s) - 1);
            }
            
            // 남은 선수는 완주하지 못한 선수
            for (String key : map.keySet()) {
                if (map.get(key) > 0) {
                    return key;
                }
            }
            
            return "";
        }
//     public String solution(String[] participant, String[] completion) {

//         String answer = "";

//         // map에 이름과 몇명인지 저장
//         Map<String, Integer> map = new HashMap<>();
//         for (String name : participant) {
//             map.put(name, map.getOrDefault(name, 0) + 1);
//         }

//         // 완주한 사람들 map에서 제거
//         for (String name : completion) {
//             map.put(name, map.get(name) - 1);
//         }

//         // 완주하지 못한 사람 출력
//         for(String key : map.keySet()) {
//             if(map.get(key) >= 1) {
//                 answer += key;
//             }
//         }
//         return answer;
//     }
}