// import java.util.*;

// class Solution {
//     public boolean solution(String[] phone_book) {
        
//         List<String> list = new ArrayList<>();
//         for (String number : phone_book) {
//             list.add(number);
//         }
        
//         Collections.sort(list, (o1, o2) -> o2.length() - o1.length());
        
//         for (int i = 0; i < list.size(); i++) {
//             for (int j = i + 1; j < list.size(); j++) {
//                 if (list.get(i).startsWith(list.get(j))) {
//                     return false;
//                 }
//             }
//         }
        
//         return true;
//     }
// }
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