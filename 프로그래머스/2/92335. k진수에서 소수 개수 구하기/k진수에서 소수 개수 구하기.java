import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        // k진수로 변환
        String str = Integer.toString(n, k);
        
        // String을 "0"을 기준으로 나누고 각 숫자가 소수인지 판단
        String[] arr = str.split("0");
        int answer = 0;
        for (String s : arr) {
            if (s.equals("") || s.equals("1")) continue;
            
            long num = Long.parseLong(s);
            if (isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    public boolean isPrime(long num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}