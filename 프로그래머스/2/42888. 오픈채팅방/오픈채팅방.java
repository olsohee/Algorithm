import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>(); // 유저 아이디, 닉네임
        
        for (String str : record) {
            if (str.split(" ")[0].equals("Leave")) continue;
            
            String userId = str.split(" ")[1];
            String nickname = str.split(" ")[2];
            map.put(userId, nickname);
        }
        
        List<String> answerList = new ArrayList<>();
        for (String str : record) {
            if (str.split(" ")[0].equals("Enter")) {
                String userId = str.split(" ")[1];
                answerList.add(map.get(userId) + "님이 들어왔습니다.");
            }
            if (str.split(" ")[0].equals("Leave")) {
                String userId = str.split(" ")[1];
                answerList.add(map.get(userId) + "님이 나갔습니다.");
            }
        }
        
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}