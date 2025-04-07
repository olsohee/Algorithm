import java.util.*;

class Solution {
    public int solution(int storey) {
        String[] stringArr = String.valueOf(storey).split("");
        int[] nums = new int[stringArr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(stringArr[i]);
        }
        
        int answer = 0;
        
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] == 5) {
                // 올림
                if (nums[i - 1] >= 5) {
                    nums[i - 1]++;
                    answer += 10 - nums[i];
                }
                //내림
                else {
                    answer += nums[i];
                }
            }
            else if (nums[i] < 5) { // 내림
                answer += nums[i];
            } else { // 올림
                nums[i - 1]++;
                answer += 10 - nums[i];
            }
        }
        
        if (nums[0] <= 5) {
            answer += nums[0];
        } else {
            answer += 10 - nums[0] + 1;
        }
        
        return answer;
    }
}