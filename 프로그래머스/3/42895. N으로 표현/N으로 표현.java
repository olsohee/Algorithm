import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        if (N == number) {
            return 1;
        }
        
        List<Set<Integer>> list = new ArrayList<>();
        
        list.add(new HashSet<>()); // 인덱스 0
        Set<Integer> set = new HashSet<>(); // 인덱스 1
        set.add(N);
        list.add(set);
        for (int i = 2; i <= 8; i++) {
            set = new HashSet<>();
            
            String str = "";
            for (int j = 0; j < i; j++) {
                str += N;
            }
            set.add(Integer.parseInt(str));
            
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
            
            for (int num : list.get(i)) {
                if (num == number) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}