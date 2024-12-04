import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        // 1. 정렬
        Arrays.sort(phone_book);
        
        // 2. 현재 번호의 접두어가 있는지 확인
        Set<String> set = new HashSet<>();
        set.add(phone_book[0]);
        for (int i = 1; i < phone_book.length; i++) {
            String number = phone_book[i];
            for (int j = 1; j <= number.length(); j++) {
                String prefix = number.substring(0, j);
                if (set.contains(prefix)) {
                    return false;
                }
            }
            set.add(number);
        }
        
        return true;
    }
}