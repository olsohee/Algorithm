
import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0); map.put('T', 0);
        map.put('C', 0); map.put('F', 0);
        map.put('J', 0); map.put('M', 0);
        map.put('A', 0); map.put('N', 0);

        for (int i = 0; i < choices.length; i++) {
            char c1 = survey[i].charAt(0);
            char c2 = survey[i].charAt(1);
            int answer = choices[i];
            switch (answer) {
                case 1:
                    map.put(c1, map.get(c1) + 3);
                    break;
                case 2:
                    map.put(c1, map.get(c1) + 2);
                    break;
                case 3:
                    map.put(c1, map.get(c1) + 1);
                    break;
                case 5:
                    map.put(c2, map.get(c2) + 1);
                    break;
                case 6:
                    map.put(c2, map.get(c2) + 2);
                    break;
                case 7:
                    map.put(c2, map.get(c2) + 3);
                    break;
            }
        }

        String answer = "";

        answer += (map.get('R') >= map.get('T')) ? 'R' : 'T';
        answer += (map.get('C') >= map.get('F')) ? 'C' : 'F';
        answer += (map.get('J') >= map.get('M')) ? 'J' : 'M';
        answer += (map.get('A') >= map.get('N')) ? 'A' : 'N';
        
        return answer;
    }
}
