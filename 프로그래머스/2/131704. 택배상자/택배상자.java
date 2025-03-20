import java.util.*;

class Solution {
    public int solution(int[] order) {
        
        int n = order.length;
        Stack<Integer> st = new Stack<>();
        
        int orderIdx = 0;
        
        for (int i = 1; i <= n; i++) {
            if (order[orderIdx] == i) {
                orderIdx++;
            } else {
                if (!st.isEmpty() && st.peek() == order[orderIdx]) {
                    st.pop();
                    orderIdx++;
                    i--;
                } else {
                    st.push(i);
                }
            }
        }
        
        while (!st.isEmpty()) {
            if (st.peek() == order[orderIdx]) {
                st.pop();
                orderIdx++;
            } else break;
        }
        
        return orderIdx;
    }
}