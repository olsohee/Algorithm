import java.util.*;

class Solution {
    
    int answer = 0; // 타겟 넘버 만드는 방법의 수 
    int[] numbers;
    int target;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
    }
    
    public void dfs(int idx, int num) {
        
        if (idx == numbers.length) {
            if (num == target) answer++;
            return;
        }
        
        dfs(idx + 1, num + numbers[idx]);
        dfs(idx + 1, num - numbers[idx]);
    }
}