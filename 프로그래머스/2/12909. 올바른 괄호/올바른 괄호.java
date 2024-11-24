import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (st.isEmpty()) {
                st.push(c);
                continue;
            }
            // 여는 괄호이면 넣기
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
        
        if (!st.isEmpty()) {
            return false;
        }
        
        return true;
    }
}