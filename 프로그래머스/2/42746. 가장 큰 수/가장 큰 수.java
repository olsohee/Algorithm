import java.util.*;

class Solution {
     public String solution(int[] numbers) {
            List<Integer> list = new ArrayList<>();
            for (int number : numbers) {
                list.add(number);
            }
            Collections.sort(list, (a, b) -> {
                String str1 = String.valueOf(a);
                String str2 = String.valueOf(b);
                return -1 * Integer.compare(Integer.parseInt(str1 + str2), Integer.parseInt(str2 + str1));
            });

            String answer = "";
            for (Integer integer : list) {
                answer += integer;
            }
         if (answer.charAt(0) == '0') {
                return "0";
            }
            return answer;
        }
}