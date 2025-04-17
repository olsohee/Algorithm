import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        
        List<Integer> answer = new ArrayList<>();
        k--;
        
        while (true) {
            if (list.size() == 1) {
                answer.add(list.get(0));
                break;
            }
            
            // int idx = (int) (k / factorial[n - 1]);
            factorial /= n;
            int idx = (int) (k / factorial);
            answer.add(list.get(idx));
            list.remove(idx);

            k = k % factorial;  
            n--;
            
        }
        
        int[] answerArr = new int[answer.size()];
        for (int i = 0; i < answerArr.length; i++) {
            answerArr[i] = answer.get(i);
        }
        return answerArr;
    }
}