import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        
        // map에 이름과 그리움 점수 저장
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        // 사진에 대한 그리움 점수 계산
        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (int j = 0; j < photo[i].length; j++) {
                if (!map.containsKey(photo[i][j])) {
                    continue;
                }
                sum += map.get(photo[i][j]);
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}