import java.util.*;

class Solution {
        public boolean solution(String[] phone_book) {

            // set에 저장
            Set<String> set = new HashSet<>();
            for (String s : phone_book) {
                set.add(s);
            }

            // 접두사가 있는지 확인 (O(N * 20))
            for (String s : phone_book) {
                for (int i = 1; i < s.length(); i++) {
                    String preStr = s.substring(0, i);
                    if (set.contains(preStr)) {
                        return false;
                    }
                }
            }

            return true;
        }
}

