import java.util.*;

class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        boolean[] visited = new boolean[people.length];
        
        int min = 0;
        for (int max = people.length - 1; max > min; max--) {
            if (people[min] + people[max] <= limit) {
                visited[min] = true;
                visited[max] = true;
                answer++;
                min++;
            }
        }
        
        for (int i = 0; i < people.length; i++) {
            if (!visited[i]) {
                answer++;
            }
        }
        return answer; // 구명보트 최소 개수
    }
}