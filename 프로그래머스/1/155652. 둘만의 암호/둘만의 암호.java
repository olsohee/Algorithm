import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        Set<Character> skipList = new HashSet<>();
        for (int i = 0; i < skip.length(); i++) {
            skipList.add(skip.charAt(i));
        }
        
        char[] answerArr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cnt = 0;
            while (cnt < index) {
                c++;
                if (c > 122) {
                    c = 97;
                }
                if (skipList.contains(c)) {
                    continue;
                } else {
                    cnt++;
                }
            }
            answerArr[i] = c;
        }
        
        String answer = "";
        for (int i = 0; i < answerArr.length; i++) {
            answer += answerArr[i];
        }
        
        return answer;
    }
}