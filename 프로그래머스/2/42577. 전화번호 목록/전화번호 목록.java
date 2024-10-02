import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> numbers = new HashSet<>();
        for (String number : phone_book) {
            numbers.add(number);
        }
        
        for (String number : phone_book) {
            for (int i = 1; i < number.length(); i++) {
                String str = number.substring(0, i);
                if (numbers.contains(str)) return false;
            }
        }
        
        return true;
    }
}