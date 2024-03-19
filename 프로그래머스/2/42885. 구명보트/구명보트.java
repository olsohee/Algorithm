import java.util.*;

class Solution {
    
    int answer = 0;
    
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int min = 0;
        // 합이 limit 초과하면 answer++, min과 다른 짝 만들기 (max--)
        for (int max = people.length - 1; max >= min; max--) {
            if (people[min] + people[max] > limit) {
                answer++;
            } else {
                answer++;
                min++;
            }
        }
        
        return answer;
    }
}