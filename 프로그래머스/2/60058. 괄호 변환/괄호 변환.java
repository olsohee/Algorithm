import java.util.*;

class Solution {
    public String solution(String p) {
        if (isCollectWord(p)) return p;
        return func(p);
    }
    
    private String func(String str) {
        if (str.equals("")) return str;
        
        // u와 v로 분리하기
        int idx = divide(str);
        String u = str.substring(0, idx + 1);
        String v = str.substring(idx + 1); // TODO 빈 문자열 되는지 확인!!!
        
        if (idx == str.length() - 1) {
            v = "";   
        }
        
        if (isCollectWord(u)) {
            return u + func(v);
        } else {
            v = func(v);
            String result = "(" + v + ")";
            //u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙이기
            String newWord = u.substring(1, u.length() - 1);
            
            for (int i = 0; i < newWord.length(); i++) {
                if (newWord.charAt(i) == '(') {
                    result += ')';
                } else {
                    result += '(';
                }
            }
            return result;
        }
    }
    
    private boolean isCollectWord(String str) {
        Stack<Character> st = new Stack<>();
        for (char c : str.toCharArray()) {
            if (st.isEmpty()) {
                st.push(c);
                continue;
            }
            if (c == '(') {
                st.push(c);
            } else {
                if (st.peek() == '(') {
                    st.pop();
                } else {
                    st.push(c);
                }
            }
        }
        
        return st.size() == 0;
    }
    
    private int divide(String str) {
        int openCnt = 0;
        int closedCnt = 0;
        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') openCnt++;
            if (str.charAt(i) == ')') closedCnt++;
            if (openCnt == closedCnt) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}