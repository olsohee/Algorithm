import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        List<Integer> list = new ArrayList<>();
        
        int[] nums = Arrays.stream(number.split(""))
            .mapToInt(string -> Integer.parseInt(string))
            .toArray();
        
        String answer = "";
        int startIdx = 0;
        int len = number.length() - k;
        while (answer.length() < len) {
            if (k == 0) {
                for (int i = startIdx; i < nums.length; i++) {
                    answer += nums[i];
                }
                break;
            }
            
            int max = nums[startIdx];
            int maxIdx = startIdx;
            for (int i = startIdx; i <= startIdx + k; i++) {
                if (nums[i] == 9) {
                    maxIdx = i;
                    break;
                }
                if (nums[i] > max) {
                    max = nums[i];
                    maxIdx = i;
                }
            }
            
            answer += nums[maxIdx];
            k -= maxIdx - startIdx;
            startIdx = maxIdx + 1;
        }
        
        return answer;
    }
}