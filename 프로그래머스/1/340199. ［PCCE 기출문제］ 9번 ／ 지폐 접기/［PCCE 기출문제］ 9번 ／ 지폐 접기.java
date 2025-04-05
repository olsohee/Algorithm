import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while (!canInsert(bill, wallet)) {
            if (bill[0] > bill[1]) {
                bill[0] = bill[0] / 2;
            } else {
                bill[1] = bill[1] / 2;
            }
            answer++;
        }
        return answer;
    }
    
    private boolean canInsert(int[] bill, int[] wallet) {
        int max = Math.max(bill[0], bill[1]);
        int min = Math.min(bill[0], bill[1]);
        if (max <= Math.max(wallet[0], wallet[1])
           && min <= Math.min(wallet[0], wallet[1])) {
            return true;
        }
        return false;
    }
}