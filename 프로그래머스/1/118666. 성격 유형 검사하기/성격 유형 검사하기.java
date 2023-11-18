import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        map.put("R", 0);
        map.put("T", 0);
        map.put("C", 0);
        map.put("F", 0);
        map.put("J", 0);
        map.put("M", 0);
        map.put("A", 0);
        map.put("N", 0);

        for (int i = 0; i < survey.length; i++) {
            // 점수를 부여할 타입과 점수 계산
            int choice = choices[i];
            int score;
            String type;
            String[] split = survey[i].split("");
            if(choice >= 1 && choice <= 3) {
                type = split[0];
                score = 4 - choice;
            } else {
                type = split[1];
                score = choice - 4;
            }
            map.put(type, map.get(type) + score);
        }

        // 결과 계산
        if(map.get("R") >= map.get("T")) {
            answer += "R";
        } else {
            answer += "T";
        }

        if(map.get("C") >= map.get("F")) {
            answer += "C";
        } else {
            answer += "F";
        }

        if(map.get("J") >= map.get("M")) {
            answer += "J";
        } else {
            answer += "M";
        }

        if(map.get("A") >= map.get("N")) {
            answer += "A";
        } else {
            answer += "N";
        }

        return answer;
    }
}