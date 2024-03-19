import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        if (N == number) {
            return 1;
        }
        
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        for (int i = 1; i <= 8; i++) {
            // i개로 만들 수 있는 숫자들의 set
            Set<Integer> set = dp.get(i);
            
            String str = "";
            for (int j = 0; j < i; j++) {
                str += N;
            }
            set.add(Integer.parseInt(str));
            
            // j개로 만드는 수 X k개로 만드는 수를 set에 넣기
            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(k)) {
                        set.add(num1 + num2);
                        set.add(num1 - num2);
                        set.add(num1 * num2);
                        if (num2 != 0) set.add(num1 / num2);
                    }
                }
            }
                     
            // set에 number이 있는지 확인
            for (int num : set) {
                if (num == number) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}