import java.util.*;

class Solution {
    boolean solution(String s) {
        
        Stack<Character> st = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            // 여는 괄호
            if (arr[i] == '(') {
                st.push(arr[i]); 
            } 
            
            // 닫는 괄호 -> pop
            else {
                if (st.isEmpty()) {
                    return false;
                }
                if (st.peek() == '(') {
                    st.pop();
                } 
            } 
        }
        
        if (!st.isEmpty()) return false;
        
        return true;
    }
}