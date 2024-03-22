import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        
        if (N == number) return 1;
        
        // i개로 만들 수 있는 수를 list에 저장
        List<Set<Integer>> list = new ArrayList<>();
        
        // 1. dp 초기화
        list.add(new HashSet<>()); // idx: 0
        
        Set<Integer> set = new HashSet<>();
        set.add(N);
        list.add(set); // idx: 1
        
        for (int i = 2; i <= 8; i++) {
            // 2. dp에 저장
            set = new HashSet<>();
            
            String s = "";
            for (int j = 0; j < i; j++) {
                s += N;
            }
            set.add(Integer.parseInt(s));
            
            for (int j = 1; j < i; j++) {
                int k = i - j;
                for (int num1 : list.get(j)) {
                    for (int num2 : list.get(k)) {
                        set.add(num1 + num2);
                        set.add(num1 - num2);
                        set.add(num1 * num2);
                        if (num2 != 0) {
                            set.add(num1 / num2);
                        }
                    }
                }
            }
            list.add(set);
            
            // 3. number가 있는지 확인
            for (int num : set) {
                if (num == number) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}