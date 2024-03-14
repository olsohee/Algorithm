import java.util.*;

class Solution {
        
    public int solution(int storey) {
        
        String[] strArr = String.valueOf(storey).split("");
        int[] arr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        
        int answer = 0;
        // 뒷자리부터 반올림하기
        for (int i = arr.length - 1; i > 0; i--) {
            // 1. 5인 경우
            if (arr[i] == 5) {
                if (arr[i - 1] >= 5) {
                    answer += 10 - arr[i];
                    arr[i - 1]++;
                    arr[i] = 0;
                }
                else {
                    answer += arr[i];
                    arr[i] = 0;
                }
            }
            
            // 2. 올림
            else if (arr[i] > 5) {
                answer += 10 - arr[i];
                arr[i - 1]++;
                arr[i] = 0;
            }
            
            // 3. 내림
            else {
                answer += arr[i];
                arr[i] = 0;
            }
        }
        
        if (arr[0] > 5) { 
            answer += 10 - arr[0];
            answer++;
        } else {
             answer += arr[0];
        }


        return answer;
    }
}