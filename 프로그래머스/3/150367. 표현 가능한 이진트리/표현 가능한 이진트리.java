import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String str = Long.toBinaryString(numbers[i]);
            while (!collectLength(str)) {
                str = "0" + str;
            }
            
            boolean result = dfs(str);
            answer[i] = result ? 1 : 0;
        }
        
        return answer;
    }
    
    public boolean collectLength(String str) {
        int len = str.length() + 1; // len이 2의 제곱이어야 함
        int n = 1;
        while(n < len) {
            n *= 2;
        }
        return len == n;
    }
    
    public boolean dfs(String str) {
        if (str.length() == 1) {
            return true;
        }
        
        int mid = str.length() / 2;
        String leftTree = str.substring(0, mid);
        String rightTree = str.substring(mid + 1);
        
        // 루트 노드가 0이면 왼쪽, 오른쪽 모든 자식 노드도 0이어야 함
        if (str.charAt(mid) == '0') {
            if (leftTree.contains("1") || rightTree.contains("1")) {
                return false;
            }
        }
        
        return dfs(leftTree) && dfs(rightTree);
    }
}