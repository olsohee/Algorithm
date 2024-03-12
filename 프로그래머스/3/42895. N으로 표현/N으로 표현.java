import java.util.*;

class Solution {
    public int solution(int N, int number) {
            
        List<Set<Integer>> dp = new ArrayList();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<Integer>());
        }
        
        dp.get(1).add(N);
        if (number == N) return 1;
        
        for (int i = 2; i <= 8; i++) {
            Set<Integer> set = dp.get(i);
            // dp.get(i)의 set에 연산 결과 담기
            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(k)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            
            String s = "";
            for (int j = 0; j < i; j++) {
                s += N;
            }
            dp.get(i).add(Integer.parseInt(s));
            
            // dp.get(i)의 set에 number가 있는지 확인
            if (dp.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}